package com.agora.jesus.gestionformacion.business.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.agora.jesus.gestionformacion.business.model.Curso;
import com.agora.jesus.gestionformacion.business.model.RolEnum;
import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.modelDTO.TareaDTO;
import com.agora.jesus.gestionformacion.business.modelDTO.UsuarioDTO;
import com.agora.jesus.gestionformacion.business.services.DetalleCurso;
import com.agora.jesus.gestionformacion.repository.CursoRepository;
import com.agora.jesus.gestionformacion.repository.DetalleCursoRepository;
import com.agora.jesus.gestionformacion.repository.TareaRepository;
import com.agora.jesus.gestionformacion.repository.UsuarioRepository;

@Service
public class DetalleCursoImpl implements DetalleCurso{
	
	private DetalleCursoRepository detalleCursoRepository;
	
	private UsuarioRepository usuarioRepository;
	
	private TareaRepository tareaRepository;
	
	private CursoRepository cursoRepository;
	
	public DetalleCursoImpl(DetalleCursoRepository detalleCursoRepository) {
		this.detalleCursoRepository = detalleCursoRepository;
	}

	@Override
	public List<UsuarioDTO> MostrarUsuarios(Long idCurso) {
		return detalleCursoRepository.findById(idCurso)
				.map(Curso::getUsuarios)
				.orElse(Collections.emptyList())
				.stream()
				.map(this::convertirDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void EliminarUsuario(Usuario localHost, Usuario eliminarUser, Long idCurso) {
		
		if(localHost.getRol().equals(RolEnum.ADMIN)) {
			Curso curso = detalleCursoRepository.finfById(idCurso);
			
			if(curso != null) {
				boolean eliminar = curso.getUsuarios().removeIf(usuario -> 
									usuario.getId().equals(eliminarUser.getId()));
				if(eliminar) {
					detalleCursoRepository.save(curso);
				} else {
					throw new RuntimeException("Usuario no encontrado en el curso");
				}
			} else {
				throw new RuntimeException("Curso no encontrado");
			}
		} else {
			throw new RuntimeException("No es Administrador");
		}
		
	}

	//IMPL INECESARIO POR AHORA 
	@Override
	public UsuarioDTO MostrarDatoUser(Long idUser) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUser);
		
		if(usuario.isPresent()) {
			return convertirDTO(usuario.get());
		} else {
			throw new RuntimeException("No existe ningun usuario con id" + idUser);
		}
	}

	@Override
	public List<TareaDTO> MostrarListaTarea(Long idCurso) {
	    return detalleCursoRepository.findById(idCurso)
	        .map(curso -> curso.getTareas()
	            .stream()
	            .map(this::convertirTareaDTO)
	            .collect(Collectors.toList()))
	        .orElse(Collections.emptyList());
	}
	
	@Override
	public Optional<?> MostrarDetalleCurso(Long idCurso) {
		Optional<Curso> curso = detalleCursoRepository.findById(idCurso);
		
		if(!curso.isPresent()) {
			throw new RuntimeException("Curso no encontrado");
		}
		
		return curso;
	}
	
	@Override
	public Tarea MostrarTarea(Long idCurso, Long idTarea) {
	    if (!cursoRepository.existsById(idCurso)) 
	        throw new RuntimeException("Curso no encontrado");
		
	    return tareaRepository.findByIdAndIdCurso(idTarea, idCurso)
	            .orElseThrow(() -> new RuntimeException(
	                tareaRepository.existsById(idTarea) ?
	                    "La tarea no pertenece al curso" :
	                    "Tarea no encontrada"
	            ));	    
	}
	
	

	//DTOS
	
	private UsuarioDTO convertirDTO(Usuario usuario) {
		return new UsuarioDTO(usuario.getNombre(), usuario.getEmail());
	}

	private TareaDTO convertirTareaDTO(Tarea tarea) {
	    TareaDTO tareaDTO = new TareaDTO();
	    tareaDTO.setIdTarea(tarea.getId());
	    tareaDTO.setNombreTarea(tarea.getNombre());
	    tareaDTO.setFechaLimite(tarea.getFechaLimite());
	    return tareaDTO;
	}

}
