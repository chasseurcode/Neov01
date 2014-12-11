package com.neo.dao;

import java.util.List;

import com.neo.domaine.Abonne;
import com.neo.domaine.Campagne;

public interface AbonneeDAO {

	public void creer(Abonne abonne);
	public void modifier(Abonne abonne);
	public void supprimer(Abonne abonne);
	public Abonne findById(int id);
	public List<Abonne> lister();
}
