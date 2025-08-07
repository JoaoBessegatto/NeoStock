package br.com.NeoStock.service;

import br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO;
import br.com.NeoStock.dto.response.VendaResponseDTO;
import br.com.NeoStock.entity.Venda;
import br.com.NeoStock.repository.ItemVendaRepository;
import br.com.NeoStock.repository.VendaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class RelatorioService {
    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;
    @Transactional(readOnly = true)
    public List<ProdutoMaisVendidoResponseDTO>listarProdutosMaisVendidos(){
        return itemVendaRepository.findProdutosMaisVendidos();
    }
    @Transactional(readOnly = true)
    public List<Venda>buscarVendasDiarias(){
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDDay = LocalDate.now().atTime(LocalTime.MAX);
        return vendaRepository.buscarVendasDoDia(startOfDay, endOfDDay);
    }

    @Transactional(readOnly = true)
    public List<Venda>buscarVendasMensais(){
        YearMonth yearMonth = YearMonth.now();
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
        return vendaRepository.buscarVendasDoMes(startOfMonth, endOfMonth);
    }

}
