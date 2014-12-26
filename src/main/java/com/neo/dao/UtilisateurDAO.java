package com.neo.dao;

import java.util.List;

import com.neo.domaine.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur findById(Long long1);
	public Utilisateur findByCompte(String compte);
	public void creer(Utilisateur utilisateur);
	public void modifier(Utilisateur utilisateur);
	public void supprimer(Utilisateur utilisateur);
	public List<Utilisateur> lister();
}
