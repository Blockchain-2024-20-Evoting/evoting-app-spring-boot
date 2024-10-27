package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.ElectionRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.ElectionResponseDTO;
import com.crymuzz.evotingapispring.service.IElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/election")
@RequiredArgsConstructor
public class ElectionController {

    private final IElectionService electionService;

    @PostMapping
    public ResponseEntity<ElectionResponseDTO> create(@RequestBody @Valid ElectionRegisterDTO electionRegisterDTO) {
        ElectionResponseDTO response = electionService.saveElection(electionRegisterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ElectionResponseDTO>> getAll() {
        return ResponseEntity.ok(this.electionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionResponseDTO> getById(@PathVariable Long id) {
        ElectionResponseDTO response = this.electionService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElection(@PathVariable Long id) {
        this.electionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
