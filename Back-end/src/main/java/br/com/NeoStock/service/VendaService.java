package br.com.NeoStock.service;

import br.com.NeoStock.dto.request.ItemVendaRequestDTO;
import br.com.NeoStock.dto.request.VendaRequestDTO;
import br.com.NeoStock.dto.response.VendaResponseDTO;
import br.com.NeoStock.entity.ItemVenda;
import br.com.NeoStock.entity.Produto;
import br.com.NeoStock.entity.Venda;
import br.com.NeoStock.exeptions.ArgumentInvalidException;
import br.com.NeoStock.exeptions.EstoqueInsuficienteException;
import br.com.NeoStock.exeptions.ProdutoNotFoundException;
import br.com.NeoStock.repository.ProdutoRepository;
import br.com.NeoStock.repository.VendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public VendaResponseDTO save (VendaRequestDTO vendaRequestDTO){
        LocalDateTime agora = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        if (vendaRequestDTO.getData().isAfter(agora)) {
            throw new ArgumentInvalidException("A data da venda não pode ser no futuro.");
        }
        Venda novaVenda = new Venda();
        List<ItemVenda> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(ItemVendaRequestDTO itemDTO : vendaRequestDTO.getItens()){
            Produto produto = produtoRepository.findById(itemDTO.getId())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
            if(produto.getQuantidadeEstoque() < itemDTO.getQuantidade()){
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + "ID: " + produto.getId() + " " + produto.getNome());
            }
            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(itemDTO.getPrecoUnitario());
            item.setSubTotal(item.getSubtotal());
            item.setVenda(novaVenda);

            itens.add(item);
            total = total.add(item.getSubtotal());

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);
        }
        novaVenda.setItens(itens);
        novaVenda.setValorTotal(total);
        novaVenda.setData(vendaRequestDTO.getData());
        novaVenda.setFormaPagamento(vendaRequestDTO.getFormaPagamento());
        Venda safeVenda = vendaRepository.save(novaVenda);
        return new VendaResponseDTO(safeVenda);
    }
}
