package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.CandidateProfileDTO;
import com.crymuzz.evotingapispring.entity.dto.CandidateRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.CandidateResponseDTO;
import com.crymuzz.evotingapispring.service.ICandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final ICandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponseDTO> registerCandidate(@RequestBody @Valid CandidateRegisterDTO candidateRegisterDTO) {
        CandidateResponseDTO response = candidateService.saveCandidate(candidateRegisterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getById(@PathVariable Long id) {
        CandidateResponseDTO response = candidateService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        List<CandidateResponseDTO> response = candidateService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<CandidateProfileDTO> getProfile(@PathVariable Long id) {
        CandidateProfileDTO response = candidateService.getProfile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        this.candidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
