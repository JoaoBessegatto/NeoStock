package br.com.NeoStock.dto.request;

import br.com.NeoStock.entity.FormaPagamento;
import br.com.NeoStock.entity.ItemVenda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VendaRequestDTO {
    private Long id;

    @NotNull(message = "A data não pode ser vazia")
    private LocalDateTime data;

    @NotNull(message = "A forma de pagamento não pode ser vazia")
    private FormaPagamento formaPagamento;

    @NotNull(message = "os itens são obrigatorios")
    private List<ItemVendaRequestDTO> Itens;
}
