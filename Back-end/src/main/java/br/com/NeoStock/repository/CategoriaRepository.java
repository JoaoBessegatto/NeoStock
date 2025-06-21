package br.com.NeoStock.repository;

import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
