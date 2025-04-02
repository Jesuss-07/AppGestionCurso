package com.agora.jesus.gestionformacion.presentation.rescontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agora.jesus.gestionformacion.business.model.Curso;
import com.agora.jesus.gestionformacion.business.model.Usuario;
import com.agora.jesus.gestionformacion.business.services.CursoService;
import com.agora.jesus.gestionformacion.presentation.controller.PresentationException;

@RestController
@RequestMapping("/res/curso")
public class CursoController {
	
	private CursoService cursoService;
	
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCurso(@RequestBody Curso curso, UriComponentsBuilder ucb){
		
		Long id = null;
		
		try {
			id=cursoService.createCurso(curso);
		}catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.created(ucb.path("curso/{id}").build(id)).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteCurse(@RequestParam(required = true) Long id){
		cursoService.borrarCurso(id);
		return ResponseEntity.ok().build();	
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCurso(@RequestBody Curso curso, @PathVariable Long id) {
		cursoService.actualizarCurso(curso, id);
	}
	
	@GetMapping()
	public List<Curso> getAll(){
		return cursoService.getAll();
	}
	
	@GetMapping("/{id}")
	public Curso getCurso(@PathVariable Long id) {
		
		Optional<Curso> optional = cursoService.getById(id);
		
		if(optional.isEmpty()) 
			throw new PresentationException("No existe ningun curso con el identificador: " + id , HttpStatus.NOT_FOUND);
		
		return optional.get();
	}
	
	@GetMapping("/{nombre}")
	public List<Curso> getByNombre(@PathVariable String nombre){
		try {
			return cursoService.getByName(nombre);
		}catch (IllegalStateException e) {
			throw new PresentationException("No se encontro ningun curso con el nombre de " + nombre, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{nombreProfesor}")
	public List<Curso> getByNombreProfesor(@PathVariable String nombreProfesor){
		try {
			return cursoService.getByProfesor(nombreProfesor);
		}catch (IllegalStateException e) {
			throw new PresentationException("No se encontro ningun curso del usuario " + nombreProfesor, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{userLogin}")
	public Optional<Curso> getByUserLogin(@RequestBody Usuario usuario){
		return cursoService.getByInscrito(usuario);
	}
	
}
