package com.agora.jesus.gestionformacion.business.services.impl;

import java.util.Optional;

import com.agora.jesus.gestionformacion.business.model.RolEnum;
import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.TareaService;
import com.agora.jesus.gestionformacion.repository.TareaRepository;
import com.agora.jesus.gestionformacion.repository.UsuarioRepository;

public class TareaServiceImpl implements TareaService {

	private TareaRepository tareaRepository;
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Tarea editarTarea(Long idTarea, Tarea nuevaTarea, Long idUsuario) throws IllegalAccessException {
		Optional<Tarea> tarea = tareaRepository.findById(idTarea);
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		
		if(tarea.isEmpty()) 
			throw new IllegalAccessException("Tarea con id " + idTarea + " no existe");
		
		if(usuario.isEmpty() || !usuario.get().getRol().equals(RolEnum.PROFESOR))
	        throw new IllegalAccessException("Solo los profesores pueden editar tareas");

		Tarea tareaExistente = tarea.get();
		
	    tareaExistente.setNombre(nuevaTarea.getNombre());
	    tareaExistente.setDescripcion(nuevaTarea.getDescripcion());
	    tareaExistente.setFechaLimite(nuevaTarea.getFechaLimite());
	    
	    return tareaRepository.save(tareaExistente);
	}

}
