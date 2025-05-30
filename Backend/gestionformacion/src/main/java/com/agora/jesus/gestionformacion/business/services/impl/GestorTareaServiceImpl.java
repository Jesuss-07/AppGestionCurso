package com.agora.jesus.gestionformacion.business.services.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.agora.jesus.gestionformacion.business.model.Curso;
import com.agora.jesus.gestionformacion.business.model.RolEnum;
import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.GestorTareaService;
import com.agora.jesus.gestionformacion.repository.CursoRepository;
import com.agora.jesus.gestionformacion.repository.TareaRepository;
import com.agora.jesus.gestionformacion.repository.UsuarioRepository;

public class GestorTareaServiceImpl implements GestorTareaService{
	
	private CursoRepository cursoRepository;
	private TareaRepository tareaRepository;
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Path eliminarArchivo(Long idCurso, Long idTarea, Long idUsuario, String nombreArchivo) throws IllegalAccessException {

	    Optional<Curso> curso = cursoRepository.findById(idCurso);
	    Optional<Tarea> tarea = tareaRepository.findById(idTarea);
	    Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

	    if (curso.isEmpty())
	        throw new IllegalAccessException("Curso con id " + idCurso + " no encontrado");

	    if (tarea.isEmpty())
	        throw new IllegalAccessException("Tarea con id " + idTarea + " no encontrada");

	    if (!comprobarExistenciaDentroCurso(curso.get(), tarea.get()))
	        throw new IllegalAccessException("La tarea con id " + idTarea + " no pertenece al curso " + curso.get().getNombre());

	    if (usuario.isEmpty())
	        throw new IllegalAccessException("Usuario con id " + idUsuario + " no encontrado");

	    Path ruta = obtenerRutaArchivo(idTarea, idUsuario, idCurso, nombreArchivo);

	    if (!Files.exists(ruta))
	        throw new IllegalAccessException("No existe ningún archivo con el nombre proporcionado: " + nombreArchivo);

	    return ruta; 
	}


	@Override
	public Path crearArchivo(Long idCurso, Long idTarea, Long idUsuario, String nombreArchivo) throws IllegalAccessException {
		
		Optional<Curso> curso = cursoRepository.findById(idCurso);
		Optional<Tarea> tarea = tareaRepository.findById(idTarea);
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		Path ruta = obtenerRutaArchivo(idTarea, idUsuario, idCurso, nombreArchivo);
		
		if(curso.isEmpty())
			throw new IllegalAccessException("Curso con id " + idCurso + " no encontrado");
			
		if(tarea.isEmpty()) 
			throw new IllegalAccessException("Tarea con id " + idTarea + " no encontrado");

		if(!comprobarExistenciaDentroCurso(curso.get(), tarea.get())) 
			throw new IllegalAccessException("Tarea con id " + idTarea + " no encontrado dentro del curso " + curso.get().getNombre());
		
		if(usuario.isEmpty()) 
			throw new IllegalAccessException("Usuario con id " + idUsuario + " no encontrado");
		
		
		if(usuario.get().getRol().equals(RolEnum.PROFESOR)) 
			throw new IllegalAccessException("Usuario " + usuario.get().getNombre() + " no es un alumno");

		if(Files.exists(ruta)) 
			throw new IllegalAccessException("El archivo ya existe");
		
		return ruta;
	}
	
	//	Metodo auxiliares
	
	private Path obtenerRutaArchivo(Long idTarea, Long idUsuario, Long idCurso, String nombreArchivo) {
		return Paths.get("Tareas/curso_" + (idCurso) + 
	                    "/tarea_" + idTarea + "/entrega_" + idUsuario + (nombreArchivo));
	}	
	
	private boolean comprobarExistenciaDentroCurso(Curso curso, Tarea tarea) {
	    return curso.getTareas().contains(tarea);
	}

}