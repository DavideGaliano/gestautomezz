package com.azienda.gestautomezz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azienda.gestautomezz.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
