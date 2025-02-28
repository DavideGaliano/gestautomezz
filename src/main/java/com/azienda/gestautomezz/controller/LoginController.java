package com.azienda.gestautomezz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("/")
	public String homepage(Model model) {
		return "pages/homepage";
	}

    @GetMapping("/pages/login")
    public String showLoginPage() {
        return "pages/login"; 
    }

}