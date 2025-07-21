package br.com.NeoStock.service;

import br.com.NeoStock.auth.Usuario;
import br.com.NeoStock.dto.request.ProdutoRequestDTO;
import br.com.NeoStock.dto.response.ProdutoResponseDTO;
import br.com.NeoStock.entity.Categoria;
import br.com.NeoStock.entity.Produto;
import br.com.NeoStock.exeptions.AuthorizationException;
import br.com.NeoStock.exeptions.CategoryException;
import br.com.NeoStock.exeptions.ProdutoNotFoundException;
import br.com.NeoStock.repository.CategoriaRepository;
import br.com.NeoStock.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto){
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!usuarioLogado.getRole().name().equals("ADMIN")){
            throw new AuthorizationException("Somente ADMIN podem cadastrar novos produtos");
        }
        Set<Categoria> categorias = dto.getCategoriaIds().stream()
                .map(id -> categoriaRepository.findById(id)
                        .orElseThrow(() -> new CategoryException("Categoria não encontrada com id: " + id)))
                .collect(Collectors.toSet());
        Produto produto = Produto.builder()
                .nome(dto.getName())
                .descricao(dto.getDescricao())
                .bigDescricao(dto.getBigDescricao())
                .preco(dto.getPreco())
                .quantidadeEstoque(dto.getQuantidadeEstoque())
                .quantidadeMinima(dto.getQuantidadeMinima())
                .imagemUrl(dto.getImagemUrl())
                .categorias(categorias)
                .build();

            Produto produtoCadastrado = produtoRepository.save(produto);
            return new ProdutoResponseDTO(produtoCadastrado);
    }
    public ProdutoResponseDTO atualizar(ProdutoRequestDTO dto){
        Produto produtoExistente = produtoRepository.findById(dto.getId())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
        Set<Categoria> categorias = dto.getCategoriaIds().stream()
                .map(id -> categoriaRepository.findById(id)
                        .orElseThrow(() -> new CategoryException("Categoria não encontrada com id: " + id)))
                .collect(Collectors.toSet());

        produtoExistente.setNome(dto.getName());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setBigDescricao(dto.getBigDescricao());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produtoExistente.setQuantidadeMinima(dto.getQuantidadeMinima());
        produtoExistente.setImagemUrl(dto.getImagemUrl());

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }
    @Transactional(readOnly = true)
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Produto> findById(Long id){
        return produtoRepository.findById(id);
    }

    public boolean delete (Long id){
        if(!produtoRepository.existsById(id)){
            return false;
        }
        produtoRepository.deleteById(id);
        return true;
    }
}
