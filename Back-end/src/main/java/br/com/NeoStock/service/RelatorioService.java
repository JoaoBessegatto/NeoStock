package br.com.NeoStock.service;

import br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO;
import br.com.NeoStock.repository.ItemVendaRepository;
import br.com.NeoStock.repository.VendaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class RelatorioService {
    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;

    public List<ProdutoMaisVendidoResponseDTO>listarProdutosMaisVendidos(){
        return itemVendaRepository.findProdutosMaisVendidos();
    }

}
