package com.neo.dao;

import java.util.List;

import com.neo.domaine.Carte;

public interface CarteDAO {

	public void creer(Carte carte);
	public void modifier(Carte carte);
	public void supprimer(Carte carte);
	public Carte findById(long id);
	public List<Carte> lister();
	public Carte getValideCarte();
}
