package com.fpmislata.movies.exceptions;

public class SQLStatementException extends RuntimeException{
    private static final String DESCRIPTION = "Error executing SQL statment";

    public SQLStatementException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
