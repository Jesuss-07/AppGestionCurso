package com.agora.jesus.gestionformacion.business.services.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.UsuarioService;
import com.agora.jesus.gestionformacion.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
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
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		Usuario user = usuarioRepository.save(usuario);
		
		return user.getId();
		
	}

	@Override
	public Optional<Usuario> login(String email, String password) {
		
		Usuario user = usuarioRepository.findByEmailAndPassword(email, password);
		
		if(user == null) {
			throw new NoSuchElementException("El usuario no existe");
		}
		
		return Optional.ofNullable(user);
	}
	
}
