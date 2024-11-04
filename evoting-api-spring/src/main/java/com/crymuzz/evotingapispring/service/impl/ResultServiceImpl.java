package com.crymuzz.evotingapispring.service.impl;

import com.crymuzz.evotingapispring.entity.CandidateEntity;
import com.crymuzz.evotingapispring.entity.ElectionEntity;
import com.crymuzz.evotingapispring.entity.ResultEntity;
import com.crymuzz.evotingapispring.entity.VoteEntity;
import com.crymuzz.evotingapispring.entity.dto.ResultResponseDTO;
import com.crymuzz.evotingapispring.entity.enums.StateElectionEnum;
import com.crymuzz.evotingapispring.exception.ResourceNotFoundException;
import com.crymuzz.evotingapispring.repository.CandidateRepository;
import com.crymuzz.evotingapispring.repository.ElectionRepository;
import com.crymuzz.evotingapispring.repository.ResultRepository;
import com.crymuzz.evotingapispring.repository.VoteRepository;
import com.crymuzz.evotingapispring.service.IResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements IResultService {
    private final ResultRepository resultRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;
    private final CandidateServiceImpl candidateServiceImpl;
    private final VoteRepository voteRepository;


    @Override
    @Transactional
    public void countVotes(ElectionEntity electionEntity) {
        List<ResultEntity> results =
                voteRepository
                        .findVoteEntityByCandidateEntityElection(electionEntity)
                        .stream()
                        .collect(
                                Collectors.groupingBy(VoteEntity::getCandidateEntity, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .map(entry -> new ResultEntity(null, entry.getKey(), entry.getValue()))
                        .toList();
        resultRepository.saveAll(results);
    }

    @Override
    public Long getVotesCandidate(Long candidateId) {
        CandidateEntity candidateEntity =
                candidateRepository.findById(candidateId).orElseThrow(() -> new ResourceNotFoundException("El " +
                        "candidato no existe"));
        if (candidateEntity.getElection().getState() != StateElectionEnum.INACTIVE)
            throw new ResourceNotFoundException("La elección aún no ha finalizado");
        return this.voteRepository.countVoteEntityByCandidateEntity(candidateEntity);
    }

    @Override
    public List<ResultResponseDTO> getResultsByElection(Long electionId) {
        ElectionEntity election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ResourceNotFoundException("La elección no existe"));

        // Verificar que la elección esté inactiva
        if (election.getState() != StateElectionEnum.INACTIVE)
            throw new ResourceNotFoundException("La elección aún no ha finalizado");

        // Obtener todos los candidatos de la elección
        List<CandidateEntity> candidates = candidateRepository.findByElection(election);

        // Contar votos por candidato y guardar en un mapa
        var votesByCandidate = voteRepository.findVoteEntityByCandidateEntityElection(election)
                .stream()
                .collect(Collectors.groupingBy(VoteEntity::getCandidateEntity, Collectors.counting()));

        // Calcular el total de votos en la elección
        long totalVotes = votesByCandidate.values().stream().mapToLong(Long::longValue).sum();

        // Encontrar el número máximo de votos obtenidos
        long maxVotes = votesByCandidate.values().stream().max(Long::compareTo).orElse(0L);

        // Crear la lista de resultados, incluyendo candidatos sin votos
        return candidates.stream()
                .map(candidate -> {
                    long voteCount = votesByCandidate.getOrDefault(candidate, 0L);
                    boolean isWinner = voteCount == maxVotes;
                    double percentage = (totalVotes > 0) ? (voteCount * 100.0) / totalVotes : 0;

                    return ResultResponseDTO.builder()
                            .firsName(candidate.getFirstName())
                            .lastName(candidate.getLastName())
                            .nameElection(candidate.getElection().getName())
                            .partyElection(candidate.getParty().getName())
                            .countVotes(voteCount)
                            .winner(isWinner)
                            .percentage(percentage)
                            .build();
                })
                .toList();
    }

}
