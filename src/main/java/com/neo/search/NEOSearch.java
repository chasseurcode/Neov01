package com.neo.search;

import java.util.List;
import java.util.Map;


public class NEOSearch {
	private String nomEntite;
	private NEOSearch suivant=null;
	
	@SuppressWarnings("rawtypes")
	protected Map<String, List> requete(String requete,Map<String,List> resultat) {
		if(suivant!=null)
			suivant.requete(requete,resultat);
		resultat.put(nomEntite, chercher(requete));
		return resultat;
	}
	public void setSuivant(NEOSearch suivant) {
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
