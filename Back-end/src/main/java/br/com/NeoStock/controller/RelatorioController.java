package br.com.NeoStock.controller;

import br.com.NeoStock.dto.response.ProdutoMaisVendidoResponseDTO;
import br.com.NeoStock.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
