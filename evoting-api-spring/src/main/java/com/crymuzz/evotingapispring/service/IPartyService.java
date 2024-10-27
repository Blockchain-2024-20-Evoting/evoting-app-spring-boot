package com.crymuzz.evotingapispring.service;

import com.crymuzz.evotingapispring.entity.dto.PartyRegisterDTO;
import com.crymuzz.evotingapispring.entity.dto.PartyResponseDTO;

import java.util.List;

public interface IPartyService {
    PartyResponseDTO saveParty(PartyRegisterDTO partyRegisterDTO);
    List<PartyResponseDTO> findAll();
    PartyResponseDTO findById(Long id);
    void delete(Long id);
}
