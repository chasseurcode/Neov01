package com.neo.dao;

import java.util.List;

import com.neo.domaine.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur parId(int id);
	public Utilisateur parCompte(String compte);
	public void creer(Utilisateur utilisateur);
	public void modifier(Utilisateur utilisateur);
	public void supprimer(Utilisateur utilisateur);
	public List<Utilisateur> lister();
}
