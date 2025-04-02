package com.agora.jesus.gestionformacion.business.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_profesor")
	private Long idProfesor;
	
	private String nombre;
	
	private String descripcion;
	
	@ManyToMany
	@JoinTable(
	    name = "curso_usuario",
	    joinColumns = @JoinColumn(name = "id_curso"),
	    inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<Usuario> usuarios;
	
	@Column(name = "numero_horas")
	private Integer numeroHoras;
	
	private Boolean habilitado;
	
	private Boolean privado;

}
