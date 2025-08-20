package br.com.NeoStock.service;

import br.com.NeoStock.auth.Usuario;
import br.com.NeoStock.dto.request.CategoriaRequestDTO;
import br.com.NeoStock.dto.response.CategoriaResponseDTO;
import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.exeptions.AuthorizationException;
import br.com.NeoStock.exeptions.CategoryException;
import br.com.NeoStock.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

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
    public CategoriaResponseDTO atualizar (CategoriaRequestDTO dto, Long id){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoryException("Não foi possivel encontrar a categoria"));
        categoriaExistente.setId(id);
        categoriaExistente.setNome(dto.getNome());
        Categoria categoriaAtualizada = categoriaRepository.save(categoriaExistente);
        return new CategoriaResponseDTO(categoriaAtualizada);
    }
    @Transactional(readOnly = true)
    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }
    public boolean delete (Long id){
        if(!categoriaRepository.existsById(id)){
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }

}
