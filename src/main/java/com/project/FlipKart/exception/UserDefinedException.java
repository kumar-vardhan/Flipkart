package com.project.FlipKart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class UserDefinedException extends Exception{

    public UserDefinedException(String exception){
        super(exception);
    }
}

@RestControllerAdvice
class UserIdExceptionHandler{

    @ExceptionHandler(UserDefinedException.class)
    public ResponseEntity<String> getMessage(UserDefinedException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}


