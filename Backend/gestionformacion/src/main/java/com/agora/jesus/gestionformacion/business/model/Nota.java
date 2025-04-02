package com.agora.jesus.gestionformacion.business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(NotaId.class)
@Entity
@Table(name = "Nota")
public class Nota {

	@Id
	@Column(name = "id_usuario")
	private Long idAlumno;
	
	@Id
	@Column(name = "id_tarea")
	private Long idTarea;
	
	private Double nota;
	
}
