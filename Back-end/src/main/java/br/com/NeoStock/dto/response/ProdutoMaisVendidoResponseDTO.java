package br.com.NeoStock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoMaisVendidoResponseDTO {
    private String nomeProduto;
    private Long quantidadeVendida;
}
