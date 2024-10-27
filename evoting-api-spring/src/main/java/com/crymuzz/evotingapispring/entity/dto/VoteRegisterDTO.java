package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

@Data
public class VoteRegisterDTO {
    private Long studentId;
    private Long candidateId;
}
