package it.epicode.be.segreteria.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorsoDiLaurea {
	
	@NotNull
	private Integer codice;
	@NotNull
	private String nome;
	private String indirizzo;
	
	private int numeroEsami;
	
	
	
	
	
	
	

}
