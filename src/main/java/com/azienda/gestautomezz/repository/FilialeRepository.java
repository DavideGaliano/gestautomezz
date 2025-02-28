package com.azienda.gestautomezz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azienda.gestautomezz.model.Filiale;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Long> {
	Filiale findByCodice(Long codice);
	Filiale deleteByCodice(Long codice);
}

