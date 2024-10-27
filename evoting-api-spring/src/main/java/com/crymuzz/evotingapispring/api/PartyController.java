package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.PartyRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.PartyResponseDTO;
import com.crymuzz.evotingapispring.service.IPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/party")
@RequiredArgsConstructor
public class PartyController {

    private final IPartyService partyService;

    @PostMapping
    public ResponseEntity<PartyResponseDTO> create(@RequestBody PartyRegisterDTO partyRegisterDTO) {
        PartyResponseDTO response = partyService.saveParty(partyRegisterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
