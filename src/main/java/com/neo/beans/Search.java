package com.neo.beans;

import java.util.List;
import java.util.Map;


public class Search {
	private String nomEntite;
	private Search suivant=null;
	
	@SuppressWarnings("rawtypes")
	protected Map<String, List> requete(String requete,Map<String,List> resultat) {
		
		if(suivant!=null)
			suivant.requete(requete,resultat);
		resultat.put(nomEntite, chercher(requete));
		
		return resultat;
	}

	public void setSuivant(Search suivant) {
		this.suivant = suivant;
	}
	
	@SuppressWarnings("rawtypes")
	protected List chercher(String req) {
		return null;
	}

	public String getNomEntite() {
		return nomEntite;
	}

	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}
	
	
}
