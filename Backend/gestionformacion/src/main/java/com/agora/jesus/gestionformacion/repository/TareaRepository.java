package com.agora.jesus.gestionformacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.jesus.gestionformacion.business.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long>{

    Optional<Tarea> findByIdAndIdCurso(Long idTarea, Long idCurso);
    
    Optional<Tarea> findById(Long id);
	
}
