package com.agora.jesus.gestionformacion.presentation.rescontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.jesus.gestionformacion.business.services.UsuarioServices;

@RestController
@RequestMapping("/rest/user")
public class UsuarioController {
	
	private UsuarioServices usuarioServices;
	
	public UsuarioController(UsuarioServices usuarioServices) {
		this.usuarioServices=usuarioServices;
	}
	

}
