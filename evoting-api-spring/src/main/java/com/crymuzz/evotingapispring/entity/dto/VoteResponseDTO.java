package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteResponseDTO {
    private String message;
    private LocalDateTime voteTime;
}

