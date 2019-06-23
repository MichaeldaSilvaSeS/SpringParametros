package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class PrincipalController {
	
	@GetMapping("/get/{view}")
	public ResponseEntity<MappingJacksonValue> get(@PathVariable(name="view") String view, 
			@RequestParam(name="name",required=false) List<String> names,
			Pessoa pessoa) {
		if(names != null) {
			System.out.println("Nao eh nulo");
			names.stream().forEach(name -> System.out.println(name));
		}
		
		if(pessoa != null) {
			System.out.println("Nao eh nulo pessoa");
			System.out.println(pessoa.getIdade());
			System.out.println(pessoa.getSobrenome());
			pessoa.getInfos().stream().forEach(info -> System.out.println(info));
		}
		
		ModelResponse response = new ModelResponse();
		response.setNome("michael");
		response.setCpf("000.000.000-00");
				
		MappingJacksonValue mapping = new MappingJacksonValue(response);
		
		if(view.equals("public"))
			mapping.setSerializationView(CustomView.Publico.class);
		else
			mapping.setSerializationView(CustomView.Privado.class);
		
	    return ResponseEntity.ok(mapping);
	}
	
	@JsonView(CustomView.Publico.class)
	@RequestMapping(value="/getview",params={"pesquisa=PUBLICO"})
	public ResponseEntity<ModelResponse> getPublico() {
				
		ModelResponse response = new ModelResponse();
		response.setNome("michael");
		response.setCpf("000.000.000-00");
		
	    return ResponseEntity.ok(response);
	}
	
	@JsonView(CustomView.Privado.class)
	@RequestMapping(value="/getview",params={"pesquisa=PRIVADA"})
	public ResponseEntity<ModelResponse> getPrivado() {
				
		ModelResponse response = new ModelResponse();
		response.setNome("michael");
		response.setCpf("000.000.000-00");
		
	    return ResponseEntity.ok(response);
	}
}
