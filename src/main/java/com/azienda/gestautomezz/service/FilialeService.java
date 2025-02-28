package com.azienda.gestautomezz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.model.FilialeRequest;
import com.azienda.gestautomezz.repository.FilialeRepository;

@Service
public class FilialeService {
	
	@Autowired
	private FilialeRepository filialeRepository;
	
	public List<Filiale> findAll() {
		return filialeRepository.findAll();
	}
	
	public Filiale findByCodice(Long codice) {
		return filialeRepository.findByCodice(codice);
	}
	
	public Filiale save(Filiale filiale) {
        return filialeRepository.save(filiale);
    }
	
	 @Transactional // Aggiunto per garantire una transazione attiva durante la rimozione
    public void deleteByCodice(Long codice) {
    	filialeRepository.deleteById(codice);
    }
	 
	 
	 public String sendFilialiData(FilialeRequest request) {
	        String url = "https://edoo.poweringsrl.it/exercises/Filiale/upload.json"; 

	        // Crea gli headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // Crea il corpo della richiesta
	        HttpEntity<FilialeRequest> entity = new HttpEntity<>(request, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

	        return response.getBody();
	    }
	
}
