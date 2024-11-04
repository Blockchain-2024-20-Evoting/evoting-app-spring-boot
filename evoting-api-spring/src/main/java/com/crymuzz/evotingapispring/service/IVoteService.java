package com.crymuzz.evotingapispring.service;

import com.crymuzz.evotingapispring.entity.dto.VoteRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.VoteResponseDTO;

public interface IVoteService {
    VoteResponseDTO vote(VoteRegisterDTO voteRegisterDTO);
}
