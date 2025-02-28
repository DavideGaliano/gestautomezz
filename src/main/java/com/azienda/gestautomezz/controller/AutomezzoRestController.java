package com.azienda.gestautomezz.controller;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.service.AutomezzoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automezzi")
public class AutomezzoRestController { 

    private final AutomezzoService automezzoService;

    public AutomezzoRestController(AutomezzoService automezzoService) {
        this.automezzoService = automezzoService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendAutomezzi(@RequestBody List<Automezzo> automezzi) {
        String response = automezzoService.sendAutomezziData(automezzi);
        return ResponseEntity.ok(response);
    }
}
