package com.agora.jesus.gestionformacion.presentation.controller;

public class HttpErrorCustomizado {

	private String error;
	
	public HttpErrorCustomizado(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
	
}
