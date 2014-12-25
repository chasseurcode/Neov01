package com.neo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neo.domaine.Campagne;


public class CampagneSearch extends NeoSearch {
	public CampagneSearch() {
		setNomEntite(Campagne.class.getName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List chercher(String req) {
		Campagne c1=new Campagne();
		c1.setDateCreation(new Date());
		c1.setIntitule("Promo de fin d'annéee");
		
		Campagne c2=new Campagne();
		c2.setDateCreation(new Date());
		c2.setIntitule("Saint Valentain");
		
		List<Campagne> campagnes=new ArrayList<Campagne>();
		campagnes.add(c1);
		campagnes.add(c2);
		return campagnes;
	}
	
	
}
