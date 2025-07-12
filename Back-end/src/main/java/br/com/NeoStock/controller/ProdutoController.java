package br.com.NeoStock.controller;

import br.com.NeoStock.dto.request.ProdutoRequestDTO;
import br.com.NeoStock.dto.response.ProdutoResponseDTO;
import br.com.NeoStock.entity.Produto;
import br.com.NeoStock.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoResponse = produtoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@Valid @RequestBody ProdutoRequestDTO dto){
        if(dto.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        ProdutoResponseDTO updateProduto = produtoService.atualizar(dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduto);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        boolean deletado = produtoService.delete(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        List<Produto> produtos = produtoService.findAll();

        if(produtos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<ProdutoResponseDTO> dtos = produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ProdutoResponseDTO>findById(@PathVariable Long id){
        return produtoService.findById(id)
                .map(produto -> ResponseEntity.ok(new ProdutoResponseDTO(produto)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
