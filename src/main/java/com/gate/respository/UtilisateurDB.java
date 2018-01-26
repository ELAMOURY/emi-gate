package com.gate.respository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gate.entite.Utilisateur;

public interface UtilisateurDB extends JpaRepository<Utilisateur,String> {
	
	@Query("select u from  Utilisateur u where u.email = ?1 and u.password = ?2")
	public Utilisateur findUtilisateur(String email,String password);
	
	}
