package br.com.NeoStock.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {
    @NotNull(message = "Id vazio/invalido")
    private Long id;
    @NotBlank(message = "O nome da categoria não pode ser vazio")
    private String nome;
}
