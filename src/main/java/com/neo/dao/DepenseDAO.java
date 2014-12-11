package com.neo.dao;

import java.util.List;

import com.neo.domaine.Depense;

public interface DepenseDAO {

	public void creer(Depense depense);
	public void modifier(Depense depense);
	public Depense findById(long id);
	public List<Depense> lister();
}
