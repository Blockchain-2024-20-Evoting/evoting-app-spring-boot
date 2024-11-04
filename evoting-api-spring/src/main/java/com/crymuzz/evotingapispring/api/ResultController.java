package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.ResultResponseDTO;
import com.crymuzz.evotingapispring.service.impl.ResultServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultServiceImpl resultService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ResultResponseDTO>> getResultElection(@PathVariable Long id) {
        List<ResultResponseDTO> response = resultService.getResultsByElection(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
