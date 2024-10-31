package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CandidateRegisterDTO {
    private String firstName;
    private String lastName;
    private MultipartFile img;
    private Long partyId;
    private Long electionId;
}
