package it.epicode.be.segreteria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.segreteria.config.SegreteriaConfig;
import it.epicode.be.segreteria.model.CorsoDiLaurea;
import it.epicode.be.segreteria.model.Segreteria;
import it.epicode.be.segreteria.model.Studente;
import it.epicode.be.segreteria.util.CorsoConverter;

@Controller
public class SegreteriaController {

	Logger log = LoggerFactory.getLogger(getClass());
	private final Segreteria segreteria;
	private final Studente studente;
	private final ApplicationContext ctx;
	private final CorsoDiLaurea corsoLaurea;
	
	

	public SegreteriaController() {
		ctx = new AnnotationConfigApplicationContext(SegreteriaConfig.class);
		segreteria = (Segreteria) ctx.getBean("segreteria");
		corsoLaurea = (CorsoDiLaurea) ctx.getBean("corsoLaurea");
		studente = (Studente) ctx.getBean("studente");
	}

	@GetMapping("/segreteria")
	public String showView(Model model) {
		return "formSegreteria";

	}

	@GetMapping("/listastudenti")
	public String listaStudenti(Model model) {
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		model.addAttribute("listaStudenti", listaStudenti);
		return "listaStudenti";

	}

	@GetMapping("/listacorsi")
	public String listaCorsi(Model model) {
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		model.addAttribute("listaCorsi", listaCorsi);
		return "listaCorsi";

	}

	@RequestMapping("/studente")
	public String showForm(Model model) {
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		System.out.println(listaCorsi.size());
		Studente studente = new Studente();
		model.addAttribute("studente", studente );
		model.addAttribute("listaCorsi", listaCorsi);
		return "addStudente";
		//return new ModelAndView("addStudente", "studente", new Studente());

	}

	@PostMapping("/addStudente")
	public String addStudenteForm(@Valid @ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result) {

		if (result.hasErrors()) {
			return "error";
		}
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		
		studente.setCorsoLaurea(corsoLaurea);
		
	    //studente.setCorsoLaurea(ctx.getBean(Segreteria.class).corsoByCodice());
	    listaStudenti.add(studente);
		model.addAttribute("studente", studente);
		
		/*model.addAttribute("matricola", studente.getMatricola());
		model.addAttribute("nome", studente.getNome());
		model.addAttribute("cognome", studente.getCognome());
		model.addAttribute("dataNascita", studente.getDataNascita());
		model.addAttribute("email", studente.getEmail());
		model.addAttribute("indirizzo", studente.getIndirizzo());
		model.addAttribute("citta", studente.getCitta());
		model.addAttribute("corsoLaurea", studente.getCorsoLaurea().getCodice());*/
		
		log.info("studente aggiunto:" + studente.toString());
		log.info("lista studenti:" + listaStudenti.toString());

		return "studenteView";
	}

	@RequestMapping("/corso")
	public ModelAndView addCorsoForm() {
		

		return new ModelAndView("addCorso", "corso", new CorsoDiLaurea());

	}

	@PostMapping("/addCorso")
	public String submit(@Valid @ModelAttribute("corso") CorsoDiLaurea corsoLaurea, ModelMap model,
			BindingResult result) {

		if (result.hasErrors()) {
			return "error";
		}
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		model.addAttribute("codice", corsoLaurea.getCodice());
		model.addAttribute("nome", corsoLaurea.getNome());
		model.addAttribute("indirizzo", corsoLaurea.getIndirizzo());
		model.addAttribute("numeroEsami", corsoLaurea.getNumeroEsami());
		listaCorsi.add(corsoLaurea);
		log.info("corso aggiunto:" + corsoLaurea.toString());
		log.info("lista corsi:" + listaCorsi.toString());

		return "corsoLaureaView";
	}
	
	@RequestMapping("/modificaStudente")
	public String updateForm(Model model) {
		
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		System.out.println(listaCorsi.size());
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		
		model.addAttribute("listaCorsi", listaCorsi);
		model.addAttribute("studente", studente);
		return "updateStudente";
		//return new ModelAndView("updateStudente", "studente", new Studente());

	}
	
	@GetMapping("/updateStudente")
	public String updateStudente(@Valid @ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "error";
		}
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		for(Studente st : listaStudenti) {
			int matricolaS = st.getMatricola();
			if(matricolaS == Integer.parseInt((String)model.getAttribute("matricola"))) {
				model.addAttribute("matricola", matricolaS);
				model.addAttribute("nome", studente.getNome());
				model.addAttribute("cognome", studente.getCognome());
				model.addAttribute("dataNascita", studente.getDataNascita());
				model.addAttribute("email", studente.getEmail());
				model.addAttribute("indirizzo", studente.getIndirizzo());
				model.addAttribute("citta", studente.getCitta());
				model.addAttribute("corsoLaurea", studente.getCorsoLaurea());
				st.setMatricola(matricolaS);
				st.setNome(studente.getNome());
				st.setCognome(studente.getCognome());
				st.setDataNascita(studente.getDataNascita());
				st.setEmail(studente.getEmail());
				st.setIndirizzo(studente.getIndirizzo());
				st.setCitta(studente.getCitta());
				st.setCorsoLaurea(studente.getCorsoLaurea());
				segreteria.getListaStudenti().add(st);
				log.info("Hai modificato lo studente: " + studente.toString() );
			}
		}
		return "studenteView";
	}
	
	@RequestMapping("/eliminaStudente")
	public String deleteForm(Model model) {
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		model.addAttribute("listaStudenti", listaStudenti);
		model.addAttribute("studente", studente);
		model.addAttribute("matricola", studente.getMatricola());
		//ModelAndView modelandview = new ModelAndView("deleteStudente", "studente", new Studente());
		return "deleteStudente";
		//return new ModelAndView("deleteStudente", "studente", studente);

	}
	
	//@DeleteMapping("/deleteStudente")
	@RequestMapping(value="/deleteStudente", method=RequestMethod.GET)
	public String deleteStudente(@ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "errorelimina";
		}
		List<Studente> listaStudenti = segreteria.getListaStudenti();
		for(Studente s : listaStudenti) {
			if(s.getMatricola() == studente.getMatricola()) {
				
				listaStudenti.remove(s);
				//model.addAttribute("listaStudenti", listaStudenti);
			log.info("Hai eliminato lo studente: " + studente.toString() );
			}
			
		}
		
		
		return "listaStudenti";
	}
	
	@RequestMapping("/eliminaCorso")
	public String deleteCorsoForm(Model model) {
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		model.addAttribute("listaCorsi", listaCorsi);
		model.addAttribute("corsoLaurea", corsoLaurea);
		model.addAttribute("codice", corsoLaurea.getCodice());
		//ModelAndView modelandview = new ModelAndView("deleteStudente", "studente", new Studente());
		return "deleteCorso";
		//return new ModelAndView("deleteStudente", "studente", studente);

	}
	
	//@DeleteMapping("/deleteCorso")
	@RequestMapping(value="/deleteCorso", method=RequestMethod.GET)
	public String deleteStudente(@ModelAttribute("corsoLaurea") CorsoDiLaurea corsoLaurea, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "erroreliminacorso";
		}
		List<CorsoDiLaurea> listaCorsi = segreteria.getListaCorsi();
		for(CorsoDiLaurea c : listaCorsi) {
			if(c.getCodice() == corsoLaurea.getCodice()) {
				
				listaCorsi.remove(c);
				//model.addAttribute("listaStudenti", listaStudenti);
			log.info("Hai eliminato il corso: " + corsoLaurea.toString() );
			}
			
		}
		
		
		return "listaCorsi";
	}
	
	

}
