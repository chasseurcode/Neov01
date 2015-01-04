package com.neo.dao;

import java.util.List;

import com.neo.domaine.Campagne;
import com.neo.domaine.Facture;

public interface CampagneDAO {

	public void creer(Campagne campagne);
	public void modifier(Campagne campagne);
	public void supprimer(Campagne campagne);
	public Campagne findById(long id);
	public List<Campagne> lister();
	public List<Campagne> listerCampAttente();
	public List<Campagne> listerCampEncours();
	public List<Campagne> listerCampTerminees();
	public void creer(Facture facture);
	public Facture findLastRecord();

}
