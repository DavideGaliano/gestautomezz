package com.azienda.gestautomezz.controller;

import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.service.FilialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiali") 
public class FilialeRestController {

    @Autowired
    private FilialeService filialeService;

    // GET - Restituisce tutte le filiali in formato JSON
    @GetMapping
    public List<Filiale> getAllFiliali() {
        return filialeService.findAll();
    }
}
