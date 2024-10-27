package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

@Data
public class CandidateResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String image;
    private String partyName;
    private String electionName;
}