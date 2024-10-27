package com.crymuzz.evotingapispring.service.impl;

import com.crymuzz.evotingapispring.entity.ElectionEntity;
import com.crymuzz.evotingapispring.entity.dto.ElectionRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.ElectionResponseDTO;
import com.crymuzz.evotingapispring.exception.BadRequestException;
import com.crymuzz.evotingapispring.repository.ElectionRepository;
import com.crymuzz.evotingapispring.service.IElectionService;
import com.crymuzz.evotingapispring.mapper.ElectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements IElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;


    @Override
    @Transactional
    public ElectionResponseDTO saveElection(ElectionRegisterDTO electionDTO) {
        boolean existElection = electionRepository.existsByName(electionDTO.getName());
        if (existElection)
            throw new BadRequestException("La elección ya existe");
        ElectionEntity electionEntity = electionMapper.toElectionEntity(electionDTO);
        LocalDate now = LocalDate.now();
        // Inactivo si no está en el rango
        electionEntity.setState(now.isAfter(electionEntity.getStartDate()) && now.isBefore(electionEntity.getEndDate()));  // Activo si la fecha actual está en el rango
        // Guarda la entidad en la base de datos
        electionEntity = electionRepository.save(electionEntity);
        // Devuelve la respuesta mapeada al DTO de respuesta
        return electionMapper.toElectionResponse(electionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectionResponseDTO> findAll() {
        return electionRepository.findAll().stream().map(electionMapper::toElectionResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ElectionResponseDTO findById(Long id) {
        Optional<ElectionEntity> response = this.electionRepository.findById(id);
        return response.map(electionMapper::toElectionResponse).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!this.electionRepository.existsById(id))
            electionRepository.deleteById(id);
        throw new IllegalArgumentException("No existe la elección con ID");
    }
}
