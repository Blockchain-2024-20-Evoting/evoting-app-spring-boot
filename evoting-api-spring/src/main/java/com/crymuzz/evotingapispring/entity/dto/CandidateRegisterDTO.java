package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

@Data
public class CandidateRegisterDTO {
    private String firstName;
    private String lastName;
    private String image;
    private Long partyId;
    private Long electionId;
}
