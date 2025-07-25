package br.com.NeoStock.controller;

import br.com.NeoStock.dto.request.VendaRequestDTO;
import br.com.NeoStock.dto.response.VendaResponseDTO;
import br.com.NeoStock.service.VendaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("venda")
@AllArgsConstructor
public class VendaController {
    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponseDTO>gerarVenda(@Valid @RequestBody VendaRequestDTO dto){
        VendaResponseDTO vendaResponse = vendaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaResponse);
    }
}
