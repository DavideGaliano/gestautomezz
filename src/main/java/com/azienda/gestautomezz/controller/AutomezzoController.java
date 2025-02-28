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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.service.AutomezzoService;
import com.azienda.gestautomezz.service.FilialeService;

@Controller
@RequestMapping("/automezzi")
public class AutomezzoController {

	@Autowired
	private AutomezzoService automezzoService;

	@Autowired
	private FilialeService filialeService;

	@GetMapping
	public String listAutomezzi(Model model) {
		List<Automezzo> automezzi = automezzoService.findAll();
		model.addAttribute("automezzi", automezzi);
		return "automezzi/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
        Automezzo automezzo = automezzoService.findByCodice(id);
        model.addAttribute("automezzo", automezzo);

        return "automezzi/show";
    }

	@GetMapping("/create")
	public String showCreateForm(Model model) {

		List<Filiale> filiali = filialeService.findAll();

		model.addAttribute("filiali", filiali);
		model.addAttribute("automezzo", new Automezzo());
		return "automezzi/create";
	}

	@PostMapping("/create")
	public String createAutomezzo(Automezzo automezzo) {
		automezzoService.save(automezzo);
		return "redirect:/automezzi";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		List<Filiale> filiali = filialeService.findAll();

		model.addAttribute("filiali", filiali);
		model.addAttribute("automezzo", automezzoService.findByCodice(id));

		return "automezzi/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Validated @ModelAttribute("automezzo") Automezzo formAutomezzo, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "/automezzi/edit";
		}
		automezzoService.save(formAutomezzo);

		return "redirect:/automezzi";
	}

	@PostMapping("/delete/{id}")
	public String deleteAutomezzo(@PathVariable("id") Long id, RedirectAttributes attributes) {
		automezzoService.deleteByCodice(id);
		attributes.addFlashAttribute("successMessage", "Automezzo con codice " + id + " è stato eliminato");

		return "redirect:/automezzi";
	}

}
