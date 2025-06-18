package br.com.NeoStock.exeptions;

public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, 400);
    }
}
