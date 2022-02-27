package it.epicode.be.segreteria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.be.segreteria.model.CorsoDiLaurea;
import it.epicode.be.segreteria.model.Segreteria;
import it.epicode.be.segreteria.model.Studente;

@Configuration
public class SegreteriaConfig {
	
	@Bean
	public Segreteria segreteria() {
		Segreteria segreteria = new Segreteria();
		
		return segreteria;
	}
	
	@Bean
	@Scope("prototype")
	public Studente studente() {
		Studente studente = new Studente();
		
		return new Studente();
	}
	
	@Bean
	@Scope("prototype")
	public CorsoDiLaurea corsoLaurea() {
		return new CorsoDiLaurea();
	}

}
