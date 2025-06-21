package br.com.NeoStock.dto.response;

import br.com.NeoStock.entity.Produto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getNome();
        this.descricao = produto.getDescricao();
        this.bigDescricao = produto.getBigDescricao();
        this.preco = produto.getPreco();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.quantidadeMinima = produto.getQuantidadeMinima();
        this.ativo = produto.getAtivo();
        this.imagemUrl = produto.getImagemUrl();
        this.nomeCategoria = produto.getCategoria().getNome();
        this.categoriaId = produto.getCategoria().getId();
        this.criadoEm = produto.getCriadoEm();
        this.atualizadoEm = produto.getAtualizadoEm();
    }
}
