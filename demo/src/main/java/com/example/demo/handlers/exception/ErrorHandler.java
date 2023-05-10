package com.example.demo.handlers.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLDataException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationErrors::new).toList());
    }

//    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handle500(SQLIntegrityConstraintViolationException exception) {
        var error = exception.getMessage();
        return ResponseEntity.internalServerError().body(error);
    }

    private record DataValidationErrors(String field, String message) {
        public  DataValidationErrors(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
