package com.azienda.gestautomezz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.azienda.gestautomezz.model.Filiale;
import com.azienda.gestautomezz.model.Role;
import com.azienda.gestautomezz.model.User;
import com.azienda.gestautomezz.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("L'username è già in uso.");
        }
        userRepository.save(user);
    }
    
    public List<User> findAll() {
		return userRepository.findAll();
	}
    
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User save(User utente) {
    	return userRepository.save(utente);
    }
    
 // Verifica se un username esiste già
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}