package com.agora.jesus.gestionformacion.business.services;

import java.util.List;
import java.util.Optional;

import com.agora.jesus.gestionformacion.business.model.Curso;
import com.agora.jesus.gestionformacion.business.model.Usuario;

public interface CursoService {
	
	Long createCurso(Curso curso);
	
	List<Curso> getAll();
	
	List<Curso> getByName(String nombre);
	
	List<Curso> getByProfesor(String nombreProfesor);
	
	Optional<Curso> getByInscrito(Usuario usuario);
	
	Optional<Curso> getById(Long id);
	
	void borrarCurso(Long id);
	
	void actualizarCurso(Curso curso, Long id);

}
