package com.mymedicalbills.restapi.exception;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException(String id) {
        super("Could not find state " + id);
    }
}
