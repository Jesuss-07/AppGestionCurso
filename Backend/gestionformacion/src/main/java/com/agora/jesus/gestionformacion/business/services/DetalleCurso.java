package com.agora.jesus.gestionformacion.business.services;

import java.util.List;
import java.util.Optional;

import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.modelDTO.TareaDTO;
import com.agora.jesus.gestionformacion.business.modelDTO.UsuarioDTO;

public interface DetalleCurso {
	
	List<UsuarioDTO>MostrarUsuarios(Long idCurso);
		
	void EliminarUsuario(Usuario localHost, Usuario eliminarUser, Long idCurso);
		
	Optional<?>MostrarDetalleCurso(Long idCurso);
	
	UsuarioDTO MostrarDatoUser(Long idUser);

	List<TareaDTO> MostrarListaTarea(Long idCurso);
	
	Tarea MostrarTarea(Long idCurso, Long idTarea);
	
}
