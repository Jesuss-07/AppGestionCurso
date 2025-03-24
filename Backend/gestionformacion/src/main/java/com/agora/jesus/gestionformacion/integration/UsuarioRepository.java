package com.agora.jesus.gestionformacion.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.jesus.gestionformacion.business.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
}
