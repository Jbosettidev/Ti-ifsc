package com.example.demo.respostas;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
//essa classe gere o log de erro asssim que o erro aparece
// Usada para indicar que o recurso solicitado não existe