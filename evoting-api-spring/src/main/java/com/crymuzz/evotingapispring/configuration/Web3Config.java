package com.crymuzz.evotingapispring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService("http://206.189.238.162:8550"));
    }

}
