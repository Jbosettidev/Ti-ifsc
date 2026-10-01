package com.example.demo.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de domínio para "não encontrado". {@code @ResponseStatus(NOT_FOUND)} define 404
 * se a exceção não for capturada por um {@code @ExceptionHandler}; neste projeto o
 * {@link GlobalExceptionHandler} monta também um {@link ApiResponse}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}