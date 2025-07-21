package br.com.NeoStock.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ProdutoRequestDTO {
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String name;

    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotBlank(message = "A descrição grande não pode ser vazia")
    private String bigDescricao;

    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin("0.0")
    private BigDecimal preco;

    @NotNull(message = "A quantidade em estoque não pode ser nulo")
    @Min(0)
    private Integer quantidadeEstoque;

    @NotNull(message = "A quantidade minima não pode ser nulo")
    @Min(0)
    private Integer quantidadeMinima;

    @NotBlank(message = "A Url da imagem não pode ser vazia")
    private String imagemUrl;

    @NotNull(message = "O id da categoria não pode ser nulo")
    private Set<Long> categoriaIds;
}
