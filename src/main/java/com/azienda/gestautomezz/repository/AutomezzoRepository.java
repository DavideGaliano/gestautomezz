package com.azienda.gestautomezz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azienda.gestautomezz.model.Automezzo;

@Repository
public interface AutomezzoRepository extends JpaRepository<Automezzo, Long> {}

