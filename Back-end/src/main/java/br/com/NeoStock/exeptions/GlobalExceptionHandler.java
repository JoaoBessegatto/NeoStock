package br.com.NeoStock.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleCustomExceptions(BaseException ex){
        ApiError error = new ApiError(HttpStatus.valueOf(ex.getStatusCode()), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder("Erros de validação: ");
        ex.getBindingResult().getFieldErrors().forEach(error ->
                sb.append("[").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("] ")
        );

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, sb.toString().trim());
        return ResponseEntity.badRequest().body(error);
    }
}

