package br.com.NeoStock.dto.response;

import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.entity.Produto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<CategoriaRecord> categorias;
    private Set<Long> categoriaIds;
    private Set<String> nomesCategorias;
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
        this.categorias = produto.getCategorias()
                .stream()
                .map(c -> new CategoriaRecord(c.getId(), c.getNome()))
                .collect(Collectors.toSet());
        this.criadoEm = produto.getCriadoEm();
        this.atualizadoEm = produto.getAtualizadoEm();

        this.categoriaIds = produto.getCategorias().stream()
                .map(Categoria::getId)
                .collect(Collectors.toSet());

        this.nomesCategorias = produto.getCategorias().stream()
                .map(Categoria::getNome)
                .collect(Collectors.toSet());
    }
}
