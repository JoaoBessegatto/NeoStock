package br.com.NeoStock.exeptions;

public class CategoryException extends BaseException {
    public CategoryException(String message) {
        super(message, 404);
    }
}
