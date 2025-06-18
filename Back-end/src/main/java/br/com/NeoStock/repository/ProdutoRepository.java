package br.com.NeoStock.repository;

import br.com.NeoStock.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCategoriaNome(String nomeCategoria);

    boolean existsByNome(String nome);
}
