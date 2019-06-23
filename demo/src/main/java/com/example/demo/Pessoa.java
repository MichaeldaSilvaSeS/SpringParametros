package com.example.demo;

import java.util.List;

import lombok.Data;

@Data
public class Pessoa {
	
	private String sobrenome;
	
	private String idade;
	
	private List<String> infos;

}
