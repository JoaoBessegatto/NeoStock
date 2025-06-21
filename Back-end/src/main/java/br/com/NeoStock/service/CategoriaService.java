package br.com.NeoStock.service;

import br.com.NeoStock.auth.Usuario;
import br.com.NeoStock.dto.request.CategoriaRequestDTO;
import br.com.NeoStock.dto.response.CategoriaResponseDTO;
import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.exeptions.AuthorizationException;
import br.com.NeoStock.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO cadastrar (CategoriaRequestDTO dto){
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!usuarioLogado.getRole().name().equals("ADMIN")){
            throw new AuthorizationException("Somente ADMIN podem cadastrar novas categorias");
        }
        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .build();
        Categoria categoriaCadastrada = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoriaCadastrada);
    }
}
