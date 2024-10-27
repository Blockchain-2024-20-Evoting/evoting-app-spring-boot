package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.VoteRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.VoteResponseDTO;
import com.crymuzz.evotingapispring.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final IVoteService voteService;

    @PostMapping
    public ResponseEntity<VoteResponseDTO> voteStudent(@RequestBody VoteRegisterDTO voteRegisterDTO) {
        VoteResponseDTO response = voteService.vote(voteRegisterDTO);
        return ResponseEntity.ok(response);
    }

}
