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
import org.springframework.web.client.HttpClientErrorException;
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
	 
	 
	 public String sendFilialiData(String email) {
		 String url = "https://edoo.poweringsrl.it/exercises/Filiale/upload.json";

		    // Recupera tutte le filiali dal database
		    List<Filiale> filiali = filialeRepository.findAll();

		    // Creiamo l'oggetto della richiesta con email e filiali
		    FilialeRequest request = new FilialeRequest(email, filiali);

		    // Creazione degli headers
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);

		    // Creazione della richiesta HTTP con la lista delle filiali e l'email
		    HttpEntity<FilialeRequest> entity = new HttpEntity<>(request, headers);

		    RestTemplate restTemplate = new RestTemplate();
		    
		    try {
		        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		        return response.getBody();
		    } catch (HttpClientErrorException e) {
		        return "Errore HTTP: " + e.getStatusCode();
		    } catch (Exception e) {
		        return "Errore generico: " + e.getMessage();
		    }
	 }
	
}
