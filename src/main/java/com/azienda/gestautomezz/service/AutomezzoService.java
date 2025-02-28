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
import com.azienda.gestautomezz.model.AutomezzoRequest;
import com.azienda.gestautomezz.repository.AutomezzoRepository;

@Service
public class AutomezzoService {
	
	private final RestTemplate restTemplate;
	
	public AutomezzoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	public String sendAutomezziData(AutomezzoRequest request) {
        String url = "https://edoo.poweringsrl.it/exercises/Automezzo/upload.json";

        // Crea gli headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crea l'oggetto della richiesta con email e automezzi
        HttpEntity<AutomezzoRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
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
