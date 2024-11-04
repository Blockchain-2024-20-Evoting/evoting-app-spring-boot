package com.crymuzz.evotingapispring.mapper;


import com.crymuzz.evotingapispring.entity.CandidateEntity;
import com.crymuzz.evotingapispring.entity.ResultEntity;
import com.crymuzz.evotingapispring.entity.dto.ResultResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultMapper {

    private final ModelMapper modelMapper;
    private final CandidateMapper candidateMapper;

    public ResultResponseDTO toResultResponseDTO(ResultEntity result) {
        CandidateEntity candidateEntity = result.getCandidate();
        return ResultResponseDTO.builder()
                .firsName(candidateEntity.getFirstName())
                .lastName(candidateEntity.getLastName())
                .nameElection(candidateEntity.getElection().getName())
                .partyElection(candidateEntity.getParty().getName())
                .countVotes(result.getVoteCount())
                .winner(false)
                .percentage(0D)
                .build();
    }


//    @Data
//    public class ResultResponseDTO {
//        private String firsName;
//        private String lastName;
//        private String nameElection;
//        private String partyElection;
//        private Long countVotes;
//        private boolean winner;
//        private double percentage;
//    }


}