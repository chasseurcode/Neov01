package com.neo.search;

import java.util.HashMap;
import java.util.List;


public class NEOSearch {
	private String nomEntite;
	private NEOSearch suivant=null;

	public NEOSearch() {
	}

	@SuppressWarnings("rawtypes")
	protected HashMap<String,List> requete(String requete,HashMap<String,List> listeR) {
		listeR.put(getNomEntite(),chercher(requete));
		if(suivant!=null)
			suivant.requete(requete,listeR);	
		return listeR;
	}

	public void setSuivant(NEOSearch suivant) {
		this.suivant = suivant;
	}

	@SuppressWarnings("rawtypes")
	protected List chercher(String requete) {
		return null;
	}

	public String getNomEntite() {
		return nomEntite;
	}

	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}
	
	public NEOSearch getSuivant() {
		return suivant;
	}

}
