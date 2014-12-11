package com.neo.dao;

import java.util.List;

import com.neo.domaine.Paiement;

public interface PaiementDAO {
	public void creer(Paiement paiement);
	public void modifier(Paiement paiement);
	public Paiement  findById(int id);
	public List<Paiement> lister();

}
