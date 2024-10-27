package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

@Data
public class CandidateProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String image;
    private PartyResponseDTO party; // Party
    private ElectionResponseDTO election; // Election
}
