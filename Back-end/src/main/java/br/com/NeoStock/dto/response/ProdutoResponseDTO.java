package br.com.NeoStock.dto.response;

import br.com.NeoStock.entity.Produto;
import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProdutoResponseDTO {
    private Long id;
    private String name;
    private String descricao;
    private String bigDescricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private Integer quantidadeMinima;
    private Boolean ativo;
    private String imagemUrl;

    private Long categoriaId;
    private String nomeCategoria;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public ProdutoResponseDTO toDTO (Produto produto){
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .name(produto.getName())
                .descricao(produto.getDescricao())
                .bigDescricao(produto.getBigDescricao())
                .preco(produto.getPreco())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .quantidadeMinima(produto.getQuantidadeMinima())
                .ativo(produto.getAtivo())
                .imagemUrl(produto.getImagemUrl())
                .categoriaId(produto.getCategoria().getId())
                .nomeCategoria(produto.getCategoria().getNome())
                .criadoEm(produto.getCriadoEm())
                .atualizadoEm(produto.getAtualizadoEm())
                .build();
    }
}
