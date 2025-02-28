package com.azienda.gestautomezz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.service.AutomezzoService;
import com.azienda.gestautomezz.service.FilialeService;

@Controller
@RequestMapping("/")
public class FilialeController {
	
	@Autowired
	private FilialeService filialeService;
	
	@Autowired
	private AutomezzoService automezzoService;
	
	@GetMapping("/pages/dashboard") 
    public String showDashboard(Model model) { //Visualizza tutte le filiali
		
		List<Filiale> filiali = filialeService.findAll();
		
		model.addAttribute("filiali", filiali);
		
        return "pages/dashboard"; 
    }
	
	
	//CREATE
	
	@GetMapping("/filiali/create")
	public String showCreateForm(Model model) {
		List<Automezzo> automezzi = automezzoService.findAll();
		model.addAttribute("automezzi", automezzi);
		model.addAttribute("filiale", new Filiale());
		return "/filiali/create";
		
	}
	
	@PostMapping("/filiali/create")
	public String store(@Validated @ModelAttribute("filiale") Filiale formFiliale ,@RequestParam(value ="automezzi", required = false) List<Long> automezziId, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors()) {
			List<Automezzo> automezzi = automezzoService.findAll(); // Recupera la lista per ripopolare il form
	        model.addAttribute("automezzi", automezzi);
			return "/filiali/create";
		}
		// Se nessun automezzo è selezionato, inizializza con una lista vuota
	    List<Automezzo> automezzi = (automezziId != null) ? automezzoService.findAllById(automezziId) : List.of();
	    formFiliale.setAutomezzi(automezzi);

	    filialeService.save(formFiliale);
	    return "redirect:/pages/dashboard";
	}
	
	//READ
	
	@GetMapping("/filiali/{codice}")
	public String show(@PathVariable("codice") Long codice, Model model) {
        Filiale filiale = filialeService.findByCodice(codice);
        model.addAttribute("filiale", filiale);
        model.addAttribute("automezzi", filiale.getAutomezzi());
        return "filiali/show";
    }
	
	//UPDATE
	
		@GetMapping("/filiali/edit/{codice}")
		public String edit(@PathVariable("codice") Long codice, Model model)
		{
			model.addAttribute("filiale", filialeService.findByCodice(codice));
			
			return "/filiali/edit";
		}
		
		@PostMapping("/filiali/edit/{codice}")
		public String update(@Validated @ModelAttribute("filiale") Filiale formFiliale ,@RequestParam(value ="automezzi", required = false) List<Long> automezziId,BindingResult bindingResult, Model model)
		{
			if(bindingResult.hasErrors()) {
				List<Automezzo> automezzi = automezzoService.findAll(); // Recupera la lista per ripopolare il form
		        model.addAttribute("automezzi", automezzi);
				return "/filiali/edit";
			}
			
			// Se nessun automezzo è selezionato, inizializza con una lista vuota
		    List<Automezzo> automezzi = (automezziId != null) ? automezzoService.findAllById(automezziId) : List.of();
		    formFiliale.setAutomezzi(automezzi);
			
			filialeService.save(formFiliale);
			
			return "redirect:/pages/dashboard";
		}
		
		//DELETE
		
		@PostMapping("/filiali/delete/{codice}")
		public String delete(@PathVariable("codice") Long codice, RedirectAttributes attributes)
		{
			filialeService.deleteByCodice(codice);
			
			attributes.addFlashAttribute("successMessage", "Filiale con codice " + codice + " è stata eliminata");
			
			return "redirect:/pages/dashboard";
		}
	

}
