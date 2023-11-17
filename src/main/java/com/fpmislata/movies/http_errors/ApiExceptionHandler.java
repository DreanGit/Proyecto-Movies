package com.fpmislata.movies.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import com.fpmislata.movies.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

@ResponseStatus(HttpStatus.NOT_FOUND)  
@ExceptionHandler({
    ResourceNotFoundException.class
})

@ResponseBody
public ErrorMessage notFoundRequest(Exception exception) {
    return new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler({
        Exception.class
})
@ResponseBody
public ErrorMessage exception(Exception exception) {
    exception.printStackTrace();
    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error");
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(HttpMessageNotReadableException.class)
@ResponseBody
public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
    return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Invalid request body");
}
}
