package br.com.NeoStock.exeptions;

public class ArgumentInvalidException extends BaseException {
    public ArgumentInvalidException(String message) {
        super(message,400);
    }
}
