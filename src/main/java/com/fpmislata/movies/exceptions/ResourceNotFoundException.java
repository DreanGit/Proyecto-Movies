package com.fpmislata.movies.exceptions;

public class ResourceNotFoundException  extends RuntimeException{
    private static final String DESCRIPTION = "Resource not found";

    public ResourceNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
    
}
