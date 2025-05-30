package com.agora.jesus.gestionformacion.business.services;

import java.nio.file.Path;

public interface GestorTareaService {
    
    Path crearArchivo(Long idCurso, Long idTarea, Long idUsuario, String nombreArchivo) throws IllegalAccessException;
    
	Path eliminarArchivo(Long idCurso, Long idTarea, Long idUsuario, String nombreArchivo)throws IllegalAccessException;	
    
}
