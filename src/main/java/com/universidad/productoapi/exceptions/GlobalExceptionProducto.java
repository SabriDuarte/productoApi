package com.universidad.productoapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionProducto {
    // Maneja excepciones cuando un producto no se encuentra
    @ExceptionHandler(EntityNotFoundExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404 NOT FOUND
    public ResponseEntity<String> handleResourceNotFound(EntityNotFoundExceptions ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 NOT FOUND + mensaje
    }

    // Excepciones generales
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 INTERNAL SERVER ERROR
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 INTERNAL SERVER ERROR +
                                                                                        // mensaje
    }
}
