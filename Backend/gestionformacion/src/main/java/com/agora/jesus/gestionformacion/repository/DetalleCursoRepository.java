package com.agora.jesus.gestionformacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.jesus.gestionformacion.business.model.Curso;

public interface DetalleCursoRepository extends JpaRepository<Curso, Long>{
	
	Curso finfById(Long id);

}
