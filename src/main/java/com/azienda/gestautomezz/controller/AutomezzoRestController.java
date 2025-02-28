package com.azienda.gestautomezz.controller;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.model.AutomezzoRequest;
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
    public ResponseEntity<String> sendAutomezzi(@RequestBody AutomezzoRequest request) {
        String response = automezzoService.sendAutomezziData(request);
        return ResponseEntity.ok(response);
    }
}