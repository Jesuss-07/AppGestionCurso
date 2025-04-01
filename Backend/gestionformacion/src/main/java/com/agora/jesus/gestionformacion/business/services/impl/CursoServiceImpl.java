package com.agora.jesus.gestionformacion.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.agora.jesus.gestionformacion.business.model.Curso;
import com.agora.jesus.gestionformacion.business.services.CursoService;
import com.agora.jesus.gestionformacion.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService{
	
	private CursoRepository cursoRepository;
	private DozerBeanMapper mapper;
	
	public CursoServiceImpl(CursoRepository cursoRepository, DozerBeanMapper mapper) {
		this.cursoRepository= cursoRepository;
		this.mapper = mapper;
	}

	@Override
	public Long createCurso(Curso curso) {

		if(curso.getId() != null) {
			throw new IllegalArgumentException  ("Para crear un curso el id tiene que ser nulo");
		}
		
		Curso curse = cursoRepository.save(curso);
		
		return curse.getId();
		
	}

	@Override
	public List<Curso> getAll() {
		return cursoRepository.findAll().stream()
						.map(x -> mapper.map(x, Curso.class))
						.toList();
	}

	@Override
	public List<Curso> getByName(String nombre) {
		return cursoRepository.findByName(nombre);
	}

	@Override
	public List<Curso> getByProfesor(String nombreProfesor) {
		return cursoRepository.findByProfesorName(nombreProfesor);
	}

	@Override
	public Optional<Curso> getById(Long id) {
		return cursoRepository.findById(id);
	}

	@Override
	public void borrarCurso(Long id) {
		
		if(!cursoRepository.existsById(id)) 
			throw new IllegalStateException("El curso con identificador " + id + " no existe");
		
		
		Curso curso = cursoRepository.findById(id).get();
		
		if(!curso.getHabilitado()) {
			throw new IllegalArgumentException ("El curso ya esta deshabilitado");
		}else {
			curso.setHabilitado(false);
			cursoRepository.save(curso);
		}
		
	}

	@Override
	public void actualizarCurso(Curso curso, Long id) {
		
		if(curso.getId() == null) {
			curso.setId(id);
		}else if (curso.getId() != id) {
			throw new IllegalArgumentException ("El que desea modificar no coincide");
		}
		
		cursoRepository.save(curso);
		
	}

}
