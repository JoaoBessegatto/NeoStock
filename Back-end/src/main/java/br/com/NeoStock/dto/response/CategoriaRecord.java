package br.com.NeoStock.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaRecord {
    private Long id;
    private String nome;
}