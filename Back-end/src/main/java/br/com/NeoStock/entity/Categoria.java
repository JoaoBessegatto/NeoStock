package br.com.NeoStock.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categoria")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",length = 45,nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
}
