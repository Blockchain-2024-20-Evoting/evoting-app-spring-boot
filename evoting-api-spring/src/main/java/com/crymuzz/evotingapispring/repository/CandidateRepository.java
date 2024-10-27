package com.crymuzz.evotingapispring.repository;

import com.crymuzz.evotingapispring.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
