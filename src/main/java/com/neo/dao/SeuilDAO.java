package com.neo.dao;

import java.util.List;

import com.neo.domaine.Seuil;

public interface SeuilDAO {
	
	public void creer(Seuil seuil);
	public void modifier(Seuil seuil);
	public void supprimer(Seuil seuil);
	public Seuil findById(Long id);
	public List<Seuil> lister();

}
