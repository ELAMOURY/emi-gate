package com.gate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gate.entite.Utilisateur;
import com.gate.metier.UtilisateurMetier;
import com.gate.respository.UtilisateurDB;

@SpringBootApplication
public class EmiGateApplication  implements CommandLineRunner {

	
	@Autowired
	UtilisateurDB userDB;
	
	@Autowired
	UtilisateurMetier metier;
	
	public static void main(String[] args) {
		SpringApplication.run(EmiGateApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
				
		userDB.save(
				new Utilisateur("boutaout","el mouloudi","boutaout@gmail.com","123456"));
		
		userDB.save(
				new Utilisateur("bennouna","ayoub","bennouna@gmail.com","654321"));
		
		 userDB.save(
				new Utilisateur("bensghir","amine","bensghir@gmail.com","123654"));
		}
}
