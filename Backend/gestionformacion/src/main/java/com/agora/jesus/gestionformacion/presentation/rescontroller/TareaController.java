package com.agora.jesus.gestionformacion.presentation.rescontroller;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agora.jesus.gestionformacion.business.model.Tarea;
import com.agora.jesus.gestionformacion.business.services.GestorTareaService;
import com.agora.jesus.gestionformacion.business.services.TareaService;

@RestController
@RequestMapping("/res/tarea")
public class TareaController {
	
	private TareaService tareaService;
	private GestorTareaService gestorTareaService;
	
	public TareaController(TareaService tareaService, GestorTareaService gestorTareaService) {
		this.tareaService = tareaService;
		this.gestorTareaService = gestorTareaService;
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editarTarea(@RequestParam Long idTarea, @RequestParam Long idUsuario, @RequestBody Tarea nuevaTarea) {
		
		try {
			Tarea tareaEditada = tareaService.editarTarea(idTarea, nuevaTarea, idUsuario);
            return ResponseEntity.ok(tareaEditada);		
        }catch(IllegalAccessException e) {
            return ResponseEntity.status(403).body(e.getMessage());
		} catch(Exception e) {
            return ResponseEntity.badRequest().body("Error al editar la tarea: " + e.getMessage());
		}
		
	}
	
	@PostMapping("/annadirArchivo")
	public ResponseEntity<?> crearArchivo(@RequestParam Long idCurso, @RequestParam Long idTarea, 
	        @RequestParam Long idUsuario, @RequestParam("archivo") MultipartFile archivoSubido) {
	    
	    try {
	        String nombreConPrefijo = "entrega_" + idUsuario + "_" + archivoSubido.getOriginalFilename();

	        Path ruta = gestorTareaService.crearArchivo(idCurso, idTarea, idUsuario, nombreConPrefijo);

	        Files.createDirectories(ruta.getParent());

	        archivoSubido.transferTo(ruta.toFile());

	        return ResponseEntity.ok("Archivo creado en: " + ruta.toString());
	    } catch (IllegalAccessException e) {
	        return ResponseEntity.status(403).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Error al crear el archivo: " + e.getMessage());
	    }
	}

	@DeleteMapping("/borrarArchivo")
	public ResponseEntity<?> eliminarArchivo(@RequestParam Long idCurso, @RequestParam Long idTarea, 
	        @RequestParam Long idUsuario, @RequestParam String nombreArchivo) {
	    
	    try {
	        Path ruta = gestorTareaService.eliminarArchivo(idCurso, idTarea, idUsuario, nombreArchivo);
	        
	        Files.delete(ruta);
	        
	        return ResponseEntity.ok("Archivo eliminado correctamente: " + ruta.toString());
	    } catch (IllegalAccessException e) {
	        return ResponseEntity.status(403).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Error al eliminar el archivo: " + e.getMessage());
	    }
	}


}
