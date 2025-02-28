package com.azienda.gestautomezz.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.model.Role;
import com.azienda.gestautomezz.model.User;
import com.azienda.gestautomezz.repository.RoleRepository;
import com.azienda.gestautomezz.repository.UserRepository;
import com.azienda.gestautomezz.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    
    @GetMapping() 
    public String listUsers(Model model) { 
		
		List<User> users = userService.findAll();
		
		model.addAttribute("users", users);
		
        return "users/index"; 
    }

    // Mostra il form di creazione di un nuovo utente
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User()); 
        model.addAttribute("userTypes", List.of("Private", "Company"));
        return "users/create";
    }

    // Salva il nuovo utente
    @PostMapping("/create")
    public String createUser(@Validated @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {

            return "users/create";  // Ritorna al form in caso di errore
        }
        
     // Codifica la password usando BCrypt
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
     // Recupera il ruolo predefinito per l'utente
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(userRole));

        
     // Salva l'utente nel database
        userService.save(user);
        
        attributes.addFlashAttribute("successMessage", "User "+ user.getUsername() +" creato");

        return "redirect:/users";  
    }
    
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes attributes) {
        userService.deleteById(id);
        attributes.addFlashAttribute("successMessage", "Utente eliminato con successo!");
        return "redirect:/users";
    }
    
}
