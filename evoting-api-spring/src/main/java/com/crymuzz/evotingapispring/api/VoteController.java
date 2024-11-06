package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.VoteRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.VoteResponseDTO;
import com.crymuzz.evotingapispring.service.IVoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gestionar operaciones relacionadas los votos
 * Proporciona los endpoints para los votos
 *
 * @author Favio Facundo
 * @version 1.0
 * @since 2024
 */


@RestController
@RequestMapping("/vote")
@Tag(name = "Votos", description = "Endpoint para los votos de las elecciones")
@RequiredArgsConstructor
public class VoteController {

    private final IVoteService voteService;

    /**
     * Registro del voto de un estudiante
     *
     * @param voteRegisterDTO contiene la infomacion del estudiante y candidato a votar
     * @return VoteResponseDTO retorna la informacion del voto
     */

    @PostMapping
    public ResponseEntity<VoteResponseDTO> voteStudent(@RequestBody VoteRegisterDTO voteRegisterDTO) {
        VoteResponseDTO response = voteService.vote(voteRegisterDTO);
        return ResponseEntity.ok(response);
    }

}
