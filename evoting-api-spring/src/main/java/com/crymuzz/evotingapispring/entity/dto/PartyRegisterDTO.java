package com.crymuzz.evotingapispring.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PartyRegisterDTO {
    @NotBlank(message = "¡El nombre del equipo no puede estar vacio!")
    private String name;
    @NotBlank(message = "¡La descripción del partido no puede estar vacia!")
    private String description;
    @NotNull(message = "La imagen del equipo es obligatoria p")
    private MultipartFile img;
}