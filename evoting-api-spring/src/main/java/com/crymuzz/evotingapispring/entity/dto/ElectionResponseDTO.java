package com.crymuzz.evotingapispring.entity.dto;

import com.crymuzz.evotingapispring.entity.enums.StateElectionEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ElectionResponseDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private StateElectionEnum state;
}