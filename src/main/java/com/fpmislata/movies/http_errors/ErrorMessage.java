package com.fpmislata.movies.http_errors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessage {
    private final int code;
    private final String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}