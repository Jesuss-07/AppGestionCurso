package com.agora.jesus.gestionformacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.jesus.gestionformacion.business.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
    Usuario findByEmailAndPassword(String email, String password);
    
	Boolean existsByEmail(String email);
}
