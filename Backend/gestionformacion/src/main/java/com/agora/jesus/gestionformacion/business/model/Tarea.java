package com.agora.jesus.gestionformacion.business.model;

import java.sql.Blob;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class Tarea {

	@Id
	private Long id; 
	
	private Long idCurso;
	private String nombre;
	private Date fechaLimite;
	private Blob archivo;
	
}
