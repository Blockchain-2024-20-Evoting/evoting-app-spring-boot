package com.crymuzz.evotingapispring.service.impl;

import com.crymuzz.evotingapispring.entity.CandidateEntity;
import com.crymuzz.evotingapispring.entity.ElectionEntity;
import com.crymuzz.evotingapispring.entity.StudentEntity;
import com.crymuzz.evotingapispring.entity.VoteEntity;
import com.crymuzz.evotingapispring.entity.dto.VoteRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.VoteResponseDTO;
import com.crymuzz.evotingapispring.entity.enums.StateElectionEnum;
import com.crymuzz.evotingapispring.exception.ElectionNotFoundException;
import com.crymuzz.evotingapispring.exception.ExcessiveVotesException;
import com.crymuzz.evotingapispring.exception.ResourceNotFoundException;
import com.crymuzz.evotingapispring.repository.CandidateRepository;
import com.crymuzz.evotingapispring.repository.ElectionRepository;
import com.crymuzz.evotingapispring.repository.StudentRepository;
import com.crymuzz.evotingapispring.repository.VoteRepository;
import com.crymuzz.evotingapispring.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements IVoteService {

    private final StudentRepository studentRepository;
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    @Override
    @Transactional
    public VoteResponseDTO vote(VoteRegisterDTO voteRegisterDTO) {
        Long candidateId = voteRegisterDTO.getCandidateId();
        Long studentId = voteRegisterDTO.getStudentId();

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante no existe"));

        CandidateEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("El candidato no fue encontrado"));

        ElectionEntity election = candidate.getElection();

        // Validar que el estudiante no haya votado en esta elección
        if (voteRepository.existsByStudentEntityAndCandidateEntityElection(student, election)) {
            throw new ExcessiveVotesException("El estudiante ya ha votado en esta elección");
        }

        // Validar que la elección esté activa
        if (election.getState() != StateElectionEnum.ACTIVE) {
            throw new ElectionNotFoundException("La elección no está activa");
        }

        // Registrar el voto
        VoteEntity vote = new VoteEntity();
        vote.setStudentEntity(student);
        vote.setCandidateEntity(candidate);
        voteRepository.save(vote);

        return createVoteResponse(vote);
    }

    private VoteResponseDTO createVoteResponse(VoteEntity voteEntity) {
        VoteResponseDTO voteResponseDTO = new VoteResponseDTO();
        voteResponseDTO.setVoteTime(LocalDateTime.now());
        voteResponseDTO.setMessage("El estudiante " + voteEntity.getStudentEntity().getFirstName() +
                " ha votado por " + voteEntity.getCandidateEntity().getFirstName() +
                " en la elección");
        return voteResponseDTO;
    }

    private List<VoteEntity> filterVotesByStudentIdAndCandidateId(Long studentId, Long candidateId) {
        return this.voteRepository.findAll().stream().filter(v -> v.getStudentEntity().getId().equals(studentId) && v.getCandidateEntity().getId().equals(candidateId)).toList();
    }

    private boolean matchVoteByStudentInElection(List<VoteEntity> votes, Long electionId) {
        return votes.stream().anyMatch(v -> v.getCandidateEntity().getId().equals(electionId));
    }

}
