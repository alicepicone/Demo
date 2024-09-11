package com.example.demo.exception;

public class NoResultsFoundException extends RuntimeException {

    public NoResultsFoundException(String message) {
        super(message);
    }
}
