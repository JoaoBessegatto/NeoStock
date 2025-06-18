package br.com.NeoStock.exeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private int status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
