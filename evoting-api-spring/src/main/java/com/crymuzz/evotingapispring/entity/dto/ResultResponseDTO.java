package com.crymuzz.evotingapispring.entity.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO para la respuesta de resultados de una elección.
 * <p>
 * Este DTO contiene la información sobre los resultados de un candidato en una elección,
 * incluyendo su nombre, el nombre de la elección, el partido, el número de votos, si es el ganador
 * y su porcentaje de votos.
 */

@Data
@Builder
public class ResultResponseDTO {
    // Primer nombre del candidato
    private String firstName;

    // Apellido del candidato
    private String lastName;

    // Nombre de la elección en la que participó el candidato
    private String nameElection;

    // Nombre del partido al que pertenece el candidato
    private String partyElection;

    // Número de votos recibidos por el candidato
    private Long countVotes;

    // Indica si el candidato es el ganador de la elección
    private boolean winner;

    // Porcentaje de votos recibidos por el candidato en la elección
    private double percentage;
}
