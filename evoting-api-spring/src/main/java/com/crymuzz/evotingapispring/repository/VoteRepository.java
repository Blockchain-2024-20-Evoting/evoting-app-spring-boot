package com.crymuzz.evotingapispring.repository;


import com.crymuzz.evotingapispring.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    boolean existsByStudentEntityAndCandidateEntityElection(StudentEntity studentEntity, ElectionEntity electionEntity);
    List<VoteEntity> findVoteEntityByCandidateEntityElection(ElectionEntity electionEntity);
    Long countVoteEntityByCandidateEntity(CandidateEntity candidateEntity);
}
