package com.crymuzz.evotingapispring.repository;


import com.crymuzz.evotingapispring.entity.CandidateEntity;
import com.crymuzz.evotingapispring.entity.ElectionEntity;
import com.crymuzz.evotingapispring.entity.StudentEntity;
import com.crymuzz.evotingapispring.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    boolean existsByStudentEntityAndCandidateEntityElection(StudentEntity studentEntity, ElectionEntity electionEntity);
}
