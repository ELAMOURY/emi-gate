package com.gate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gate.metier.UtilisateurMetier;

@Controller
public class InscriptionController {
	
	@Autowired
	UtilisateurMetier metier;
	

}
