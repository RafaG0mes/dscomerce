package br.com.rgsystems.dscomerce.services.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }
}
