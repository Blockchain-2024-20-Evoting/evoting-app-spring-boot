package com.crymuzz.evotingapispring.mapper;

import com.crymuzz.evotingapispring.entity.ResultEntity;
import com.crymuzz.evotingapispring.entity.dto.ResultResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultMapper {

    private final ModelMapper modelMapper;

    public ResultResponseDTO toResultResponseDTO(ResultEntity resultEntity) {
        return modelMapper.map(resultEntity, ResultResponseDTO.class);
    }
}
