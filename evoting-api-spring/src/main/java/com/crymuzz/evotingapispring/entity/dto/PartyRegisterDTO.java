package com.crymuzz.evotingapispring.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PartyRegisterDTO {
    @NotBlank(message = "El campo del equipo no puede ser nulo o en blanco")
    private String name;
    @NotBlank(message = "La descripción del equipo no puede ser nula")
    private String description;
    private String img;
}
