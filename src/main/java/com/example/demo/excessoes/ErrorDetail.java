package com.example.demo.excessoes;

import lombok.Getter;

@Getter
public class ErrorDetail {
    private String field;
    private String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
