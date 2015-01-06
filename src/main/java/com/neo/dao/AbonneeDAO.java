package com.neo.dao;

import java.util.List;

import com.neo.domaine.Abonne;

public interface AbonneeDAO {

	public void creer(Abonne abonne);
	public Abonne findByCompte(String compte);
	public void modifier(Abonne abonne);
	public void supprimer(Abonne abonne);
	public Abonne findById(Long long1);
	public List<Abonne> lister();
}
