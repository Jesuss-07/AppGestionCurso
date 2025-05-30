package com.agora.jesus.gestionformacion.business.services;

import com.agora.jesus.gestionformacion.business.model.Tarea;

public interface TareaService {

	public Tarea editarTarea(Long idTarea, Tarea nuevaTarea, Long idUsuario) throws IllegalAccessException;
		
}
