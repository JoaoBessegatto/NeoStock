package br.com.NeoStock.repository;

import br.com.NeoStock.entity.Venda;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query("SELECT v FROM Venda v WHERE v.data BETWEEN :startOfDay AND :endOfDay")
    List<Venda> buscarVendasDoDia(@Param("startOfDay") LocalDateTime startOfDay,
                                  @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT v FROM Venda v WHERE v.data BETWEEN :startOfMonth AND :endOfMonth")
    List<Venda> buscarVendasDoMes(@Param("startOfMonth") LocalDateTime startOfMonth,
                                  @Param("endOfMonth") LocalDateTime endOfMonth);

    @Override
    @EntityGraph(attributePaths = {"itens", "itens.produto", "usuario"})
    Optional<Venda> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"itens", "itens.produto", "usuario"})
    List<Venda> findAll();
}


