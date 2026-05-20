package com.example.demo.excessoes;

import lombok.Getter;

/**
 * Um item da lista {@link ApiResponse#getErrors()}: nome do campo (ou objeto) e mensagem
 * de validação associada.
 */
@Getter
public class ErrorDetail {
    private String field;
    private String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
