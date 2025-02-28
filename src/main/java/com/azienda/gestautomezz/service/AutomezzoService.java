package com.azienda.gestautomezz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azienda.gestautomezz.model.Automezzo;
import com.azienda.gestautomezz.repository.AutomezzoRepository;

@Service
public class AutomezzoService {
	
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
