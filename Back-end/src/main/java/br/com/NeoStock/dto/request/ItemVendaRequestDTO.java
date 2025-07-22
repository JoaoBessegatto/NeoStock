package br.com.NeoStock.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemVendaRequestDTO {
    private Long id;

    @NotNull(message = "a quantidade é obrigatoria")
    @Min(value = 1,message = "A quantidade minima é 1")
    private Integer quantidade;

    @NotNull(message = "o preço não pode ser vazio")
    @Min(value = 1, message = "O valor minimo é 1")
    private BigDecimal precoUnitario;
}
