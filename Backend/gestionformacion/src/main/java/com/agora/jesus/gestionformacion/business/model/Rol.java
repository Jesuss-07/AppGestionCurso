package com.agora.jesus.gestionformacion.business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Rol {
	
	@Id
	private Long id;
	
	@Column(name = "nombre_rol")
	@Enumerated(EnumType.STRING)
	private RolEnum nombre;
	
	private String Descripcion;

}
