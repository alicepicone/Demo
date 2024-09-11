package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoResultsFoundException.class)
    public final ResponseEntity<ErrorResponse> exceptionNoResultsFound(Exception exception) {
        ErrorResponse error = new ErrorResponse();

        error.setCodice(HttpStatus.NOT_FOUND.value());
        error.setMessaggio(exception.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<ErrorResponse> exceptionInvalidInput(Exception exception) {
        ErrorResponse error = new ErrorResponse();

        error.setCodice(HttpStatus.BAD_REQUEST.value());
        error.setMessaggio((exception.getMessage()));

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
