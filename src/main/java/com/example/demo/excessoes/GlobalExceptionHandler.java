package com.example.demo.excessoes;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tratamento global de exceções para toda a API: padroniza status HTTP, mensagem e caminho.
 * <p>
 * {@code @RestControllerAdvice} aplica estes handlers a controllers {@code @RestController}.
 * A ordem importa quando vários handlers poderiam casar; aqui tipos específicos vêm antes do
 * {@link Exception} genérico (500).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /** Recurso não encontrado: resposta 404 com {@link ApiResponse}. */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ex.printStackTrace();

        ApiResponse response = new ApiResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Falha de validação Bean Validation ({@code @Valid}): 400 com lista de {@link ErrorDetail}
     * por campo.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        ex.printStackTrace();

        // Converte BindingResult em lista de campo + mensagem
        List<ErrorDetail> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = (error instanceof FieldError) ? ((FieldError) error).getField() : error.getObjectName();
                    String errorMessage = error.getDefaultMessage();
                    return new ErrorDetail(fieldName, errorMessage);
                })
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação nos campos enviados",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /** JSON ilegível, parâmetro obrigatório ausente ou tipo de path incompatível: 400. */
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ApiResponse> handleBadRequestExceptions(
            Exception ex,
            HttpServletRequest request) {

        ex.printStackTrace();

        ApiResponse response = new ApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida ou malformada",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        ApiResponse response = new ApiResponse(
                HttpStatus.BAD_REQUEST.value(),
                "E-mail ou nome de usuário já cadastrado",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /** Qualquer outra exceção não tratada: 500 com mensagem genérica ao cliente. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ex.printStackTrace();

        ApiResponse response = new ApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}