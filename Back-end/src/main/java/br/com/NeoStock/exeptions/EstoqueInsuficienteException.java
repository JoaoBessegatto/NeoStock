package br.com.NeoStock.exeptions;

public class EstoqueInsuficienteException extends BaseException {
    public EstoqueInsuficienteException(String message) {
        super(message, 409);
    }
}
