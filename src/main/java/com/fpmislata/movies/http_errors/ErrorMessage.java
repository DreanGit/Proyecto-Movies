package com.fpmislata.movies.http_errors;

public class ErrorMessage {
    
    private final String message;
    private final int code;
 
    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
