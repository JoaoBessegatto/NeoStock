package br.com.NeoStock.exeptions;

public class ProdutoNotFoundException extends BaseException {
    public ProdutoNotFoundException(String message) {
        super(message, 404);
    }
}
