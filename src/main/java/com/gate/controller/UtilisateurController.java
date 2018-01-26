package com.gate.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gate.entite.Compte;
import com.gate.entite.Utilisateur;
import com.gate.metier.UtilisateurMetier;

@Controller
public class UtilisateurController  {
	
	@Autowired
	UtilisateurMetier metier;
	
	@RequestMapping("/accueil")
	public String index(){
		return "index";
	}
	
	/*@RequestMapping("/seConnecter")
	public String connecter(Model model,String email,String psw,HttpServletRequest request){
		try{
		Utilisateur utilisateur = metier.consulter(email, psw);
		HttpSession session = request.getSession();
		session.setAttribute("user",utilisateur);
		model.addAttribute("user",utilisateur);
		}catch(Exception  e){
			model.addAttribute("error",e);
		}
		return "index";
	}*/
	
	@RequestMapping(value="/seConnecter",method=RequestMethod.POST)
	public String connecter(Model model,String email,String psw,String rememberMe,HttpServletRequest request,
			HttpServletResponse response,HttpSession session)
	{
		try{
		Utilisateur utilisateur = metier.consulter(email, psw);
		model.addAttribute("user",utilisateur);
		session.setAttribute("email",utilisateur.getEmail());
		session.setAttribute("nom",utilisateur.getName());
		session.setAttribute("prenom",utilisateur.getPrenom());
		if(rememberMe!=null){
			Cookie ckEmail = new Cookie("email",utilisateur.getEmail());
			Cookie ckPsw = new Cookie("psw",utilisateur.getPassword());
			ckEmail.setMaxAge(3600);
			ckPsw.setMaxAge(3600);
			response.addCookie(ckPsw);
			response.addCookie(ckEmail);
		}}
		catch(Exception e){
			model.addAttribute("error",e);
		}
		return"index";
	}
	
	@RequestMapping(value="/seConnecter",method=RequestMethod.GET)
	public String connecter()
	{
		return "redirect:accueil";
		
	}
	
	@RequestMapping(value="/accueil",method=RequestMethod.GET)
	public String connecterGet(Model model,HttpServletRequest request,
			HttpSession session)
	{
		try{
		Compte compte = metier.chercherCookie(request);
		if(compte!=null){
			Utilisateur utilisateur = metier.consulter(compte.getEmail(),compte.getPassword());
			model.addAttribute("user",utilisateur);
			session.setAttribute("email",utilisateur.getEmail());
			session.setAttribute("nom",utilisateur.getName());
			session.setAttribute("prenom",utilisateur.getPrenom());
		}}catch(Exception e){}
		return"index";
	}
	
	@RequestMapping(value="/deconnecter")
	public String deconnecter(HttpServletRequest request,HttpServletResponse resp,HttpSession session)
	{
		try{
		session = request.getSession();
		session.invalidate();
		metier.deconnecte(request, resp);}
		catch(Exception e){}
		return"index";
	}
	
	
	@RequestMapping(value="/inscription",method=RequestMethod.POST)
	public String creer(Utilisateur u)
	{
		try{
			
			metier.creerUtilisateur(u);
			
		}
		catch(Exception e){}
		return"index";
	}
	
	
	
	
	@RequestMapping(value="/inscription")
	public String cre()
	{
	
		return"inscription";
	}
	
	
	
	
	
	

}
