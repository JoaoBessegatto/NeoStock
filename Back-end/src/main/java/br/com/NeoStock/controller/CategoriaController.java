package br.com.NeoStock.controller;

import br.com.NeoStock.dto.request.CategoriaRequestDTO;
import br.com.NeoStock.dto.response.CategoriaResponseDTO;
import br.com.NeoStock.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {
    CategoriaService categoriaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO>cadastrar(@Valid @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO categoriaResponse = categoriaService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponse);
    }

}
