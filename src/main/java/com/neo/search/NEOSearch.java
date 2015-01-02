package com.neo.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NEOSearch {
	private String nomEntite;
	private NEOSearch suivant=null;
	@SuppressWarnings("rawtypes")
	private Map<String,List> resultat;
	
	@SuppressWarnings("rawtypes")
	public NEOSearch() {
		resultat=new HashMap<String, List>();
	}

	@SuppressWarnings("rawtypes")
	protected Map<String, List> requete(String requete) {

		if(suivant!=null)
			suivant.requete(requete);

		this.resultat.put(nomEntite, chercher(requete));
		return this.resultat;
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

	@SuppressWarnings("rawtypes")
	public Map<String,List> getResultat() {
		return resultat;
	}

	@SuppressWarnings("rawtypes")
	public void setResultat(Map<String,List> resultat) {
		this.resultat = resultat;
	}

}
