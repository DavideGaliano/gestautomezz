package com.azienda.gestautomezz.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.repository.AutomezzoRepository;

@Service
public class AutomezzoService {
	
	private final RestTemplate restTemplate;
	
	public AutomezzoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	public String sendAutomezziData(List<Automezzo> automezzi) {
        String url = "https://edoo.poweringsrl.it/exercises/Automezzo/upload.json";

        // Username e password per autenticazione
        String username = "admin";
        String password = "admin123";

        // Creazione dell'header con Basic Authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<List<Automezzo>> requestEntity = new HttpEntity<>(automezzi, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Errore durante l'invio: " + e.getMessage();
        }
    }
	
	@Autowired
    private AutomezzoRepository automezzoRepository;

    public List<Automezzo> findAll() {
        return automezzoRepository.findAll();
    }
    
    public List<Automezzo> findAllById(List<Long> id) {
    	return automezzoRepository.findAllById(id);
    }

    public Automezzo save(Automezzo automezzo) {
        return automezzoRepository.save(automezzo);
    }

    public void deleteByCodice(Long id) {
    	automezzoRepository.deleteById(id);
    }

    public Automezzo findByCodice(Long id) {
        return automezzoRepository.findById(id).orElse(null);
    }

}
