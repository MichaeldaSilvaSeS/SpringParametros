package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class ModelResponse {
	
	@JsonView(CustomView.Publico.class)
	private String nome;
	
	@JsonView(CustomView.Privado.class)
	private String cpf;
	
}
