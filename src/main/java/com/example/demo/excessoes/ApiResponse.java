package com.example.demo.excessoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import java.util.Collections;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)  // ← Mudar para NON_EMPTY
public class ApiResponse {
    private int status;
    private String message;
    private String path;
    private Long id;
    private List<ErrorDetail> errors;

    // Construtor com ID (para POST, PUT, PATCH)
    public ApiResponse(int status, String message, String path, Long id) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.id = id;
        this.errors = Collections.emptyList();
    }

    // Construtor sem ID (para DELETE)
    public ApiResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.id = null;
        this.errors = Collections.emptyList();
    }

    // Construtor para erros
    public ApiResponse(int status, String message, String path, List<ErrorDetail> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.id = null;
        this.errors = errors != null ? errors : Collections.emptyList();
    }
}