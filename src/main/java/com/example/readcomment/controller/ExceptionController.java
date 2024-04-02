package com.example.readcomment.controller;

import com.example.readcomment.dto.APIError;
import com.example.readcomment.exception.OkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(OkException.class)
    public final ResponseEntity<APIError> badRequestException(OkException ex) {
        APIError apiError = new APIError(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.OK);
    }
}
