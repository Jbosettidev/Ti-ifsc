package com.example.demo.excessoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import java.util.List;

/**
 * Corpo JSON padronizado para erros da API: código HTTP, mensagem, URI da requisição e,
 * opcionalmente, lista de {@link ErrorDetail} (validação).
 * <p>
 * {@code @JsonInclude(NON_NULL)} omite a propriedade {@code errors} no JSON quando for nula.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private int status;
    private String message;
    private String path;
    private List<ErrorDetail> errors;

    /**
     * Erros simples (ex.: 404, 500) sem lista de campos.
     */
    public ApiResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    /**
     * Erro de validação (400) com detalhes por campo.
     */
    public ApiResponse(int status, String message, String path, List<ErrorDetail> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }
}