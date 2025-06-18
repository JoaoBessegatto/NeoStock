package br.com.NeoStock.auth;

import br.com.NeoStock.dto.request.UsuarioRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<?> logar(@RequestBody Login login) { // Alterado para ResponseEntity<?>
		try {
			String token = loginService.logar(login);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			// Senha ou usuário inválido
			return new ResponseEntity<>("Credenciais inválidas. Verifique seu usuário e senha.", HttpStatus.UNAUTHORIZED); // 401
		} catch (AuthenticationException e) {
			// Outros erros de autenticação (usuário desabilitado, etc.)
			return new ResponseEntity<>("Erro de autenticação: " + e.getMessage(), HttpStatus.UNAUTHORIZED); // 401
		} catch (Exception e) {
			// Qualquer outra exceção inesperada
			return new ResponseEntity<>("Ocorreu um erro inesperado durante o login.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {
		dto.setRole(Role.USER);
		usuarioService.cadastrar(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/cadastrar-admin")
	public ResponseEntity<Void> cadastrarAdmin(@RequestBody @Valid UsuarioRequestDTO dto) {
		dto.setRole(Role.ADMIN);
		usuarioService.cadastrar(dto);
		return ResponseEntity.ok().build();
	}
}
