package com.crymuzz.evotingapispring.exception;

public class ErrorResourceImageException  extends RuntimeException {

    public ErrorResourceImageException(String message) {
            super(message);
        }

    public ErrorResourceImageException() {
            super("Recurso erroneo de imagen");
        }
}
