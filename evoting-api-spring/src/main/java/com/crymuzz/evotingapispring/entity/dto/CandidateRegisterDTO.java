package com.crymuzz.evotingapispring.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO para el registro del candidato
 */

@Data
public class CandidateRegisterDTO {
    // Nombre del candidato. No puede estar vacío o en blanco.
    @NotBlank(message = "El campo del nombre del candidato estar nulo o en blanco")
    private String firstName;

    // Apellido del candidato. No puede estar vacío o en blanco.
    @NotBlank(message = "El campo del apellido del candidato estar nulo o en blanco")
    private String lastName;

    // Imagen del candidato. No puede estar vacía.
    @NotEmpty(message = "El candidato necesita una imagen")
    private MultipartFile img;

    // ID del partido al que pertenece el candidato. No puede estar vacío o en blanco.
    @NotBlank(message = "El campo del partido no puede estar nulo o en blanco")
    private Long partyId;

    // ID de la elección en la que participa el candidato. No puede estar vacío o en blanco.
    @NotBlank(message = "El campo de la eleccion no puede estar nulo o en blanco")
    private Long electionId;
}
