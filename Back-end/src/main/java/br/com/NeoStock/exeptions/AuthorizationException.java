package br.com.NeoStock.exeptions;

public class AuthorizationException extends BaseException {
    public AuthorizationException(String message) {
        super(message, 403);
    }
}
