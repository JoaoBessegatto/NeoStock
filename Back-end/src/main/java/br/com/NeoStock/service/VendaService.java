package br.com.NeoStock.service;

import br.com.NeoStock.repository.VendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;


}
