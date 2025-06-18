package br.com.NeoStock.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsername(String login);
	public boolean existsByUsername(String username);


}
