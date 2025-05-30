package com.agora.jesus.gestionformacion.business.services;

import java.util.Optional;

import com.agora.jesus.gestionformacion.business.model.Usuario;

public interface UsuarioService {
	
	Long registro(Usuario usuario);
	
	Optional<Usuario> login(String email, String password);

}
