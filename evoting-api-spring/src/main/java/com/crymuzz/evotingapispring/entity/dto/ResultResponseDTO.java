package com.crymuzz.evotingapispring.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponseDTO {
    private String firsName;
    private String lastName;
    private String nameElection;
    private String partyElection;
    private Long countVotes;
    private boolean winner;
    private double percentage;
}
