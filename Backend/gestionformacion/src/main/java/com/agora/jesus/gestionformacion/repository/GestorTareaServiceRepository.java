package com.agora.jesus.gestionformacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agora.jesus.gestionformacion.business.model.Tarea;

public interface GestorTareaServiceRepository extends JpaRepository<Tarea, Long>{

}
