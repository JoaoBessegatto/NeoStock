package br.com.NeoStock.controller;

import br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO;
import br.com.NeoStock.dto.response.VendaResponseDTO;
import br.com.NeoStock.entity.Venda;
import br.com.NeoStock.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("relatorio")
@AllArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping("/mais-vendidos")
    public ResponseEntity<List<ProdutoMaisVendidoResponseDTO>>findBestSeller(){
        List<ProdutoMaisVendidoResponseDTO> maisVendidos = relatorioService.listarProdutosMaisVendidos();
        return ResponseEntity.ok(maisVendidos);
    }

    @GetMapping("/vendas-diarias")
    public ResponseEntity<List<VendaResponseDTO>>findDailySales(){
        List<Venda> vendas = relatorioService.buscarVendasDiarias();

        if (vendas.isEmpty()){
            ResponseEntity.ok(Collections.emptyList());
        }
        List<VendaResponseDTO> vendasDto = vendas.stream()
                .map(VendaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(vendasDto);
    }

    @GetMapping("/vendas-mensais")
    public ResponseEntity<List<VendaResponseDTO>>findMonthlySales(){
        List<Venda> vendas = relatorioService.buscarVendasMensais();

        if (vendas.isEmpty()){
            ResponseEntity.ok(Collections.emptyList());
        }

        List<VendaResponseDTO> vendaDTO = vendas.stream()
                .map(VendaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(vendaDTO);
    }
}
