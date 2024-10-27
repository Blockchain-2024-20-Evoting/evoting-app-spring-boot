package com.crymuzz.evotingapispring.service;

import com.crymuzz.evotingapispring.entity.dto.VoteRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.VoteResponseDTO;


public interface IVoteService {
    VoteResponseDTO vote(VoteRegisterDTO voteRegisterDTO);
    // List<PartyResponseDTO> findAll();
    // PartyResponseDTO findById(Long id);
    // void delete(Long id);
}
