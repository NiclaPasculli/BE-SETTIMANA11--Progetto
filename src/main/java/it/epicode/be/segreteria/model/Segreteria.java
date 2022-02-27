package it.epicode.be.segreteria.model;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Segreteria {
	
	private final List<Studente> listaStudenti = new ArrayList<>();
	private final List<CorsoDiLaurea> listaCorsi = new ArrayList<>();
	
	
	
	public CorsoDiLaurea corsoByCodice(String codice) {
		CorsoDiLaurea corso = null;
		for(CorsoDiLaurea c : listaCorsi) {
			if(c.getCodice().equals(codice)) {
				corso = c;
			}
		}
		
		return corso;
		
	}
}
