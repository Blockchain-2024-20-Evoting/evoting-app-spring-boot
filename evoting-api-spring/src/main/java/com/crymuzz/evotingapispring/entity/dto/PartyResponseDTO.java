package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;


@Data
public class PartyResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String img;
}
