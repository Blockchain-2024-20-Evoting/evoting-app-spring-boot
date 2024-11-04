package com.crymuzz.evotingapispring.exception;

public class ElectionNotFoundException extends RuntimeException {

    public ElectionNotFoundException(String message) {
        super(message);
    }

    public ElectionNotFoundException() {
        super("Election not found");
    }


}
