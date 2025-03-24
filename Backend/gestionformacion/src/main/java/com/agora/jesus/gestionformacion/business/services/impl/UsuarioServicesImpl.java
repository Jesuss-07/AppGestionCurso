package com.agora.jesus.gestionformacion.business.services.impl;

import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.UsuarioServices;
import com.agora.jesus.gestionformacion.integration.UsuarioRepository;

public class UsuarioServicesImpl implements UsuarioServices {
	
	UsuarioRepository usuarioRepository;

	public UsuarioServicesImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Long registro(Usuario usuario) {
		
		if(usuario.getId() != null){
			throw new IllegalStateException("Para crear un usuario el id tiene que ser nulo.");
		}
		
		if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {
			throw new IllegalStateException("El email ya existe porfavor introduzca otro.");
		}
		
		Usuario user = usuarioRepository.save(usuario);
		
		return user.getId();
		
	}

	
	
}
