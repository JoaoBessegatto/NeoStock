package br.com.NeoStock.dto.response;

import br.com.NeoStock.entity.ItemVenda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ItemVendaResponseDTO {
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subTotal;

    public ItemVendaResponseDTO(ItemVenda item){
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.subTotal = item.getSubtotal();
    }
}
