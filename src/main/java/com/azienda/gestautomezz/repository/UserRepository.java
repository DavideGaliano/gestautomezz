package com.azienda.gestautomezz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azienda.gestautomezz.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);  // Metodo per verificare se l'username esiste
	 User findByUsername(String username);
}