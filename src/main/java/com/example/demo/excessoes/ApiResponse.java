package com.example.demo.excessoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Não mostra a lista de erros se ela estiver vazia
public class ApiResponse {
    private int status;
    private String message;
    private String path;
    private List<ErrorDetail> errors;

    // Construtor para erros simples (404, 500)
    public ApiResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Construtor para erros com detalhes (Validação 400)
    public ApiResponse(int status, String message, String path, List<ErrorDetail> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }
}