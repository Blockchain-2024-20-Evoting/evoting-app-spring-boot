package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.PartyRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.PartyResponseDTO;
import com.crymuzz.evotingapispring.service.IPartyService;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/v1/party")
@RequiredArgsConstructor
public class PartyController {

    private final IPartyService partyService;

    @PostMapping
    public ResponseEntity<PartyResponseDTO> create(@Valid @ModelAttribute PartyRegisterDTO partyRegisterDTO) {
        PartyResponseDTO response = partyService.saveParty(partyRegisterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<Resource> getPartyImg(@PathVariable Long id) throws IOException {
        Resource response = partyService.findImgPartyById(id);
        String contentType = Files.probeContentType(response.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(response);
    }


    @GetMapping
    public ResponseEntity<List<PartyResponseDTO>> getAll() {
        return ResponseEntity.ok(partyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyResponseDTO> getById(@PathVariable Long id) {
        PartyResponseDTO response = partyService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        this.partyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
