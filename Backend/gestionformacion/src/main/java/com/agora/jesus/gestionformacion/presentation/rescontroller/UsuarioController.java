package com.agora.jesus.gestionformacion.presentation.rescontroller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.UsuarioService;
import com.agora.jesus.gestionformacion.presentation.controller.PresentationException;

@RestController
@RequestMapping("/rest/user")
public class UsuarioController {
	
	private UsuarioService usuarioServices;
	
	public UsuarioController(UsuarioService usuarioServices) {
		this.usuarioServices=usuarioServices;
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucb){
		
		Long id = null;
		
		try {
			id = usuarioServices.registro(usuario);
		}catch (IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.created(ucb.path("/user/{id}").build(id)).build();
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam(required = true) String email, @RequestParam(required = true) String password){
		
		Optional<Usuario> user = usuarioServices.login(email, password);
		
		if(!user.isPresent()) {
			throw new PresentationException("Usuario o contraseña incorrecto", HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(user);
		
	}
	
}
