package com.neo.dao;

import java.util.List;

import com.neo.domaine.Reglement;

public interface ReglementDAO {

	public void creer(Reglement reglement);
	public void modifier(Reglement reglement);
	public Reglement findById(int id);
	public List<Reglement> lister();
}
