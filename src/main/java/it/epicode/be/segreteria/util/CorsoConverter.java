package it.epicode.be.segreteria.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteria.controller.SegreteriaController;
import it.epicode.be.segreteria.model.CorsoDiLaurea;
import it.epicode.be.segreteria.model.Segreteria;

@Component
public class CorsoConverter implements Converter<String, CorsoDiLaurea> {

	@Autowired
	ApplicationContext ctx;
	
	/*@Override
	public CorsoDiLaurea convert(Integer codice) {
	
		return ctx.getBean(SegreteriaController.class).getCorsoByCodice;
	}*/

	

	

	@Override
	public CorsoDiLaurea convert(String codice) {
		// TODO Auto-generated method stub
		return (CorsoDiLaurea) ctx.getBean(Segreteria.class).corsoByCodice(codice);
	}

}
