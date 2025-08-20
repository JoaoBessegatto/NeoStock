package br.com.NeoStock.controller;

import br.com.NeoStock.dto.request.CategoriaRequestDTO;
import br.com.NeoStock.dto.response.CategoriaResponseDTO;
import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO>cadastrar(@Valid @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO categoriaResponse = categoriaService.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoriaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(categoriaResponse);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO>atualizar(@Valid @RequestBody CategoriaRequestDTO dto, @PathVariable Long id){
        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        CategoriaResponseDTO updateCategory = categoriaService.atualizar(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<CategoriaResponseDTO>> findAll(){
        List<Categoria> categorias = categoriaService.findAll();

        if(categorias.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<CategoriaResponseDTO> dtos = categorias.stream()
                .map(CategoriaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CategoriaResponseDTO>findById(@PathVariable Long id){
        return categoriaService.findById(id)
                .map(categoria -> ResponseEntity.ok(new CategoriaResponseDTO(categoria)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deletado = categoriaService.delete(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
