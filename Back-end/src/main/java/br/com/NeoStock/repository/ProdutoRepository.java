package br.com.NeoStock.repository;

import br.com.NeoStock.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT p FROM Produto p JOIN p.categorias c WHERE c.nome = :nome")
    List<Produto> findByCategoriasNome(@Param("nome") String nome);


    boolean existsByNome(String nome);

    void deleteById(long id);
}
