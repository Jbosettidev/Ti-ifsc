package com.example.demo.respostas;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler { //essa classe gera o Json do erro com todos os dados do ApiResponse,
    //funbciona agr so com o 404 e 500
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handle404(//404 e esperado (nao achado)
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ex.printStackTrace(); //gera um mini log, (opcional)

        ApiResponse response = new ApiResponse( //aqui gera o Json de resposta
                404,
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);//sai a resposta
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handle500(//500 e bug
            Exception ex,
            HttpServletRequest request) {
        ex.printStackTrace();

        ApiResponse response = new ApiResponse(//gera o Json de resposta
                500,
                "Erro interno",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);//sai a resposta
    }
}