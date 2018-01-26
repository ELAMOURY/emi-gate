package com.gate.metier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gate.entite.Compte;
import com.gate.entite.Utilisateur;

public interface UtilisateurMetier {
	

	public Utilisateur consulter(String email,String psw);
	
	public void creerUtilisateur(Utilisateur user);
	
	public void supprimerUtilisateur(Utilisateur user);
	
	public void modifierUtilisateur(Utilisateur user);
	
	public Compte chercherCookie(HttpServletRequest request);
	
	public void deconnecte(HttpServletRequest request,HttpServletResponse resp);
	
}
