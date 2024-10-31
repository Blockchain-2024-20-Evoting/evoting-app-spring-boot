package com.crymuzz.evotingapispring.service.impl;

import com.crymuzz.evotingapispring.entity.CandidateEntity;
import com.crymuzz.evotingapispring.entity.ElectionEntity;
import com.crymuzz.evotingapispring.entity.PartyEntity;
import com.crymuzz.evotingapispring.entity.dto.CandidateProfileDTO;
import com.crymuzz.evotingapispring.entity.dto.CandidateRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.CandidateResponseDTO;
import com.crymuzz.evotingapispring.exception.BadRequestException;
import com.crymuzz.evotingapispring.exception.ResourceNotFoundException;
import com.crymuzz.evotingapispring.mapper.CandidateMapper;
import com.crymuzz.evotingapispring.repository.CandidateRepository;
import com.crymuzz.evotingapispring.repository.ElectionRepository;
import com.crymuzz.evotingapispring.repository.PartyRepository;
import com.crymuzz.evotingapispring.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateServiceImpl implements ICandidateService {

    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;
    private final ElectionRepository electionRepository;
    private final CandidateMapper candidateMapper;
    private final StorageServiceImpl storageService;

    @Override
    @Transactional
    public CandidateResponseDTO saveCandidate(CandidateRegisterDTO candidateRegisterDTO) {
        String imgPath = storageService.store(candidateRegisterDTO.getImg());
        if(imgPath.isBlank() || imgPath.isEmpty())
            throw new IllegalArgumentException("Recurso no válido");
        if (candidateRepository.existsByFirstNameAndLastName(candidateRegisterDTO.getFirstName(), candidateRegisterDTO.getLastName()))
            throw new BadRequestException("El candidato ya existe");

        PartyEntity party = partyRepository.findById(candidateRegisterDTO.getPartyId())
                .orElseThrow(() -> new ResourceNotFoundException("El partido especificado no existe"));
        ElectionEntity election = electionRepository.findById(candidateRegisterDTO.getElectionId())
                .orElseThrow(() -> new ResourceNotFoundException("La elección especificada no existe"));

        CandidateEntity candidateEntity = candidateMapper.toCandidateEntity(candidateRegisterDTO, party, election);
        candidateEntity.setImage(imgPath);
        CandidateEntity savedCandidate = candidateRepository.save(candidateEntity);
        return candidateMapper.toCandidateResponseDTO(savedCandidate);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource findImgCandidateById(Long id) {
        CandidateEntity candidateEntity = candidateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El candidato no existe"));
        String imgPath = candidateEntity.getImage();
        if (imgPath.isEmpty() || imgPath.isBlank())
            throw new IllegalArgumentException("No se ha encontrado la imagen");
        return storageService.loadSource(imgPath);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateResponseDTO findById(Long id) {
        CandidateEntity candidate = candidateRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return candidateMapper.toCandidateResponseDTO(candidate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateResponseDTO> findAll() {
        return candidateRepository.findAll().stream().map(candidateMapper::toCandidateResponseDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateProfileDTO getProfile(Long id) {
        CandidateEntity candidateEntity = candidateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El candidato no existe"));
        return candidateMapper.toCandidateProfileDTO(candidateEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.candidateRepository.deleteById(id);
    }
}
