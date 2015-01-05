package com.neo.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


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
	protected void requete(String requete) {
		if(suivant!=null){
			suivant.requete(requete);
		}
	}

	public void setSuivant(NEOSearch suivant) {
		this.suivant = suivant;
	}

	@SuppressWarnings("rawtypes")
	protected void chercher(String requete) {
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

	public NEOSearch getSuivant() {
		return suivant;
	}

}
