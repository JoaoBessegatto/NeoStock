package br.com.NeoStock.auth;

import br.com.NeoStock.dto.request.UsuarioRequestDTO;
import br.com.NeoStock.exeptions.UserInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrar(UsuarioRequestDTO dto) {
        if(usuarioRepository.existsByUsername(dto.getUsername())){
            throw new UserInvalidException("Usuario já cadastrado!");
        }else {
            Usuario usuario = new Usuario();
            usuario.setUsername(dto.getUsername());
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
            usuario.setRole(dto.getRole());

            usuarioRepository.save(usuario);
        }
    }
}

