package com.mymedicalbills.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StateNotFoundAdvice {
    @ExceptionHandler(StateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stateNotFoundHandler(StateNotFoundException ex){
        return ex.getMessage();
    }
}
