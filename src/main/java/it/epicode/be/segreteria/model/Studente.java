package it.epicode.be.segreteria.model;




import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studente {
	
	@NotNull
	private Integer matricola;
	@NotNull
	private String nome;
	@NotNull
	private String cognome;
	@NotNull
	
	private String dataNascita;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String indirizzo;
	@NotNull
	private String citta;
	private CorsoDiLaurea corsoLaurea;
	
	
	@Override
	public String toString() {
		return "Studente matricola: " + matricola + ", nome: " + nome + ", cognome: " + cognome + ", dataNascita: "
				+ dataNascita + ", email: " + email + ", indirizzo: " + indirizzo + ", citta: " + citta + ", corsoLaurea: "
				+ corsoLaurea + "";
	}
	
	
	

}
