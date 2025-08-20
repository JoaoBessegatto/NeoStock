package br.com.NeoStock.controller;

import br.com.NeoStock.dto.request.VendaRequestDTO;
import br.com.NeoStock.dto.response.VendaResponseDTO;
import br.com.NeoStock.entity.Venda;
import br.com.NeoStock.service.VendaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.event.ListDataEvent;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("venda")
@AllArgsConstructor
public class VendaController {
    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponseDTO>gerarVenda(@Valid @RequestBody VendaRequestDTO dto){
        VendaResponseDTO vendaResponse = vendaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(vendaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(vendaResponse);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deletado = vendaService.delete(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<VendaResponseDTO>getById(@PathVariable Long id){
        return vendaService.findById(id)
                .map(venda -> ResponseEntity.ok(new VendaResponseDTO(venda)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>>getAll(){
        List<Venda> vendas = vendaService.findAll();

        if(vendas.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<VendaResponseDTO> vendasDtos = vendas.stream()
                .map(VendaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(vendasDtos);
    }
}
