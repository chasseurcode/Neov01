package com.neo.dao;

import java.util.List;

import com.neo.domaine.Tarif;

public interface TarifDAO {

	public void creer(Tarif tarif);
	public void modifier(Tarif tarif);
	public void supprimer(Tarif tarif);
	public Tarif findById(long id);
	public List<Tarif>lister();
}
