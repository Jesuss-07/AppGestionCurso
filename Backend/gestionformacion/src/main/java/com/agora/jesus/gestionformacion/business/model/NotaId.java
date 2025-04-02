package com.agora.jesus.gestionformacion.business.model;

import java.io.Serializable;

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
public class NotaId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	private Long idTarea;
	
}
