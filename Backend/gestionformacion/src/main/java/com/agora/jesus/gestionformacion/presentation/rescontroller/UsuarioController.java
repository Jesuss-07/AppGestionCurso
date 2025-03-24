package com.agora.jesus.gestionformacion.presentation.rescontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.UsuarioServices;
import com.agora.jesus.gestionformacion.presentation.controller.PresentationException;

@RestController
@RequestMapping("/rest/user")
public class UsuarioController {
	
	private UsuarioServices usuarioServices;
	
	public UsuarioController(UsuarioServices usuarioServices) {
		this.usuarioServices=usuarioServices;
	}
	
	@PostMapping
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucb){
		
		Long id = null;
		
		try {
			id = usuarioServices.registro(usuario);
		}catch (IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.created(ucb.path("/user/{id}").build(id)).build();
	}
	
}
