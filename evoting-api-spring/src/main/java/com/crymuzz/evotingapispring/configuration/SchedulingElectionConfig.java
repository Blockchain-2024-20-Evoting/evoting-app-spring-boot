package com.crymuzz.evotingapispring.configuration;

import com.crymuzz.evotingapispring.service.IElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulingElectionConfig {

    private final IElectionService electionService;

    @Scheduled(cron = "0 * * * * *", zone = "America/Lima")
    public void scheduleElection() {
        electionService.updateAllStatusElection();
    }

}
