package com.gate.metier;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gate.entite.Compte;
import com.gate.entite.Utilisateur;
import com.gate.respository.UtilisateurDB;


@Service
@Transactional
public class UtilisateurMetierImp  implements UtilisateurMetier{
	
	@Autowired
	UtilisateurDB metier;

	@Override
	public Utilisateur consulter(String email, String psw) {
		// TODO Auto-generated method stub
		if(email==null || psw==null) throw new RuntimeException ("veulliez verifier vos données");
		Utilisateur utilisateur = metier.findUtilisateur(email, psw);
		return utilisateur;
	}

	@Override
	public void creerUtilisateur(Utilisateur user) {
		metier.save(user);
	}

	@Override
	public void supprimerUtilisateur(Utilisateur user) {
		metier.delete(user);
		
	}

	@Override
	public void modifierUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		metier.save(user);
	}
	
	
	public Compte chercherCookie(HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
		Compte compte = null;
		
		String email ="";
		String psw = "";
		
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("email"))
				email = (String)cookie.getValue();
			if(cookie.getName().equalsIgnoreCase("psw"))
				psw = (String)cookie.getValue();
		}
		
		if(!email.isEmpty() && !psw.isEmpty()){
			compte = new Compte(email,psw);
		}
		
		return compte;
		
	}
	
	public void deconnecte(HttpServletRequest request,HttpServletResponse resp){
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("email")){
			cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);}
			
			if(cookie.getName().equalsIgnoreCase("psw")){
			cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);}
		}
		
		
		
	}

}
