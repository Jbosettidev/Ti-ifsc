package com.example.demo.respostas;

import lombok.Getter;

@Getter
public class ApiResponse {

    private final int status;
    private final String message;
    private final String path;

    public ApiResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
//essa classe cria o log, da pra ele o status ex (404,500), uma mensagem definida, e o caminho do erro