package com.azienda.gestautomezz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azienda.gestautomezz.model.Filiale;
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
	
}
