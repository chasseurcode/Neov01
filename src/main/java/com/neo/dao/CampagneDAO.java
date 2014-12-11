package com.neo.dao;

import java.util.List;

import com.neo.domaine.Campagne;

public interface CampagneDAO {

	public void creer(Campagne campagne);
	public void modifier(Campagne campagne);
	public void supprimer(Campagne campagne);
	public Campagne findById(int id);
	public List<Campagne> lister();
}
