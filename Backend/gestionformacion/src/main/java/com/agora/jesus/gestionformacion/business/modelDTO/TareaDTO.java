package com.agora.jesus.gestionformacion.business.modelDTO;

import java.sql.Date;

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
public class TareaDTO {

	private Long idTarea;
	
	private String nombreTarea;
	
	private Date fechaLimite;
	
}
