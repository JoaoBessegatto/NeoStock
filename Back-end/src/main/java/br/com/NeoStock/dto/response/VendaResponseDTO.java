package br.com.NeoStock.dto.response;

import br.com.NeoStock.entity.FormaPagamento;
import br.com.NeoStock.entity.Venda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VendaResponseDTO {
    private Long id;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private FormaPagamento formaPagamento;
    private List<ItemVendaResponseDTO> itens;

    public VendaResponseDTO(Venda venda){
        this.id = venda.getId();
        this.data = venda.getData();
        this.valorTotal = venda.getValorTotal();
        this.itens = venda.getItens().stream()
                .map(ItemVendaResponseDTO::new)
                .collect(Collectors.toList());
    }
}
