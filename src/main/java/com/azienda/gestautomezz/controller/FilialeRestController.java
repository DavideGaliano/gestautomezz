package com.azienda.gestautomezz.controller;

import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.model.FilialeRequest;
import com.azienda.gestautomezz.service.FilialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiali") 
public class FilialeRestController {

    @Autowired
    private FilialeService filialeService;
    
    public FilialeRestController(FilialeService filialeService) {
        this.filialeService = filialeService;
    }

    // GET - Restituisce tutte le filiali in formato JSON
    @GetMapping
    public List<Filiale> getAllFiliali() {
        return filialeService.findAll();
    }
    
 // POST - Invia le filiali insieme all'email
    @PostMapping("/send")
    public ResponseEntity<String> sendFiliali() {
    	String email = "admin@example.com";
        String response = filialeService.sendFilialiData(email);
        return ResponseEntity.ok(response);
    }
}
