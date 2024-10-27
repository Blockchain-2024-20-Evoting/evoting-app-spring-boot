package com.crymuzz.evotingapispring.exception;

public class ExcessiveVotesException extends RuntimeException {

    public ExcessiveVotesException(String message) {
        super(message);
    }

    public ExcessiveVotesException() {
        super("Se ha excedido el número máximo de votos permitidos para esta elección.");
    }
}
