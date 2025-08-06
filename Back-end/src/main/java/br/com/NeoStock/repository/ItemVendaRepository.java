package br.com.NeoStock.repository;

import br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO;
import br.com.NeoStock.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    @Query("SELECT new br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO(i.produto.nome, SUM(i.quantidade)) " +
            "FROM ItemVenda i GROUP BY i.produto.id, i.produto.nome ORDER BY SUM(i.quantidade) DESC")
    List<ProdutoMaisVendidoResponseDTO> findProdutosMaisVendidos();
}
