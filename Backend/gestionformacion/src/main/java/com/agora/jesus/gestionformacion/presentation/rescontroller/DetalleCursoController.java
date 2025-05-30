package com.agora.jesus.gestionformacion.presentation.rescontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.modelDTO.TareaDTO;
import com.agora.jesus.gestionformacion.business.modelDTO.UsuarioDTO;
import com.agora.jesus.gestionformacion.business.services.DetalleCurso;
import com.agora.jesus.gestionformacion.presentation.controller.HttpErrorCustomizado;
import com.agora.jesus.gestionformacion.presentation.controller.PresentationException;
import com.agora.jesus.gestionformacion.repository.DetalleCursoRepository;
import com.agora.jesus.gestionformacion.repository.UsuarioRepository;

@RestController
@RequestMapping("/rest/user")
public class DetalleCursoController {
	
	private final DetalleCurso detalleCurso;
    private final UsuarioRepository usuarioRepository;

    public DetalleCursoController(DetalleCurso detalleCurso, UsuarioRepository usuarioRepository, DetalleCursoRepository detalleCursoRepository) {
        this.detalleCurso = detalleCurso;
        this.usuarioRepository = usuarioRepository;
    }
	
	@GetMapping("/getUsersByCourse")
    public ResponseEntity<?> getUsersByCourse(@RequestParam Long idCurso) {
        try {
            List<UsuarioDTO> usuarios = detalleCurso.MostrarUsuarios(idCurso);
            
            if (usuarios.isEmpty()) {
                throw new PresentationException("El curso no existe o no tiene usuarios", HttpStatus.NOT_FOUND);
            }
            
            return ResponseEntity.ok(usuarios);
            
        } catch (RuntimeException e) {
            throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

	    /*
	    * Metodo encargado de eliminar un usuario en un curso se desglosa con 3 partes validacion de la variable id curso 
	    * id usuario e id administrador, despues se utiliza el impl para ejecutar la acción en caso contrario el catch 
	    * 
	    */
	@DeleteMapping("/deleteCourse")
	public ResponseEntity<?> eliminarUsuarioDeCurso(@RequestParam("courseId") Long idCurso, 
			@RequestParam("userId") Long idUsuarioEliminar, @RequestParam("adminId") Long idAdmin) {

		try {

	    Usuario admin = usuarioRepository.findById(idAdmin)
	    		.orElseThrow(() -> new PresentationException("Credenciales inválidas", HttpStatus.UNAUTHORIZED));

	    Usuario usuarioEliminar = usuarioRepository.findById(idUsuarioEliminar)
	                .orElseThrow(() -> new PresentationException("Usuario no existente", HttpStatus.NOT_FOUND));

	    detalleCurso.EliminarUsuario(admin, usuarioEliminar, idCurso);

	    return ResponseEntity.ok()
	    		.body(new HttpErrorCustomizado(String.format("Usuario eliminado del curso", 
	                       						usuarioEliminar.getEmail(), idCurso)));

	        } catch (RuntimeException e) {
	        	
	            HttpStatus status = switch(e.getMessage()) {
	                case "No es admin" -> HttpStatus.FORBIDDEN;
	                case "Curso no encontrado" -> HttpStatus.NOT_FOUND;
	                case "Usuario no encontrado en el curso" -> HttpStatus.BAD_REQUEST;
	                default -> HttpStatus.INTERNAL_SERVER_ERROR;
	            };
	            throw new PresentationException(e.getMessage(), status);
	        }
	    }
	
		@GetMapping("/getTarea")
		public ResponseEntity<Tarea> getTarea(@RequestParam  Long idCurso, @RequestParam  Long idTarea) {
		    
		    Tarea tarea = detalleCurso.MostrarTarea(idCurso, idTarea);
		    return ResponseEntity.ok(tarea);
		}
		
		@GetMapping("/Curso/getTareas")
		public ResponseEntity<List<TareaDTO>> getTareas(@RequestParam Long idCurso){
			   
			 List<TareaDTO> tareas = detalleCurso.MostrarListaTarea(idCurso);
		        
		     if (tareas.isEmpty()) {
		    	 return ResponseEntity.noContent().build(); 
		     }
		        
		     return ResponseEntity.ok(tareas);
			    
		}
	
}