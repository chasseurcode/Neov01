package com.neo.search;

import java.util.List;
import java.util.Map;

public class NEOEngine {
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, List> chercher(String requete) {
		
		NEOSearch clientSearch=new ClientSearch();
		NEOSearch campSearch=new CampagneSearch();
		NEOSearch pubSearch=new PubSearch();
		NEOSearch abonneSearch=new AbonneSearch(); 
		
		clientSearch.setSuivant(campSearch);
		campSearch.setSuivant(pubSearch);
		pubSearch.setSuivant(abonneSearch);
		
		clientSearch.requete(requete);
		//retourner le resultat au bout de la chaine
		return pubSearch.getResultat();
	}
	
	public static String[] suggestion(String mot) {
		return SuggestionIndex.getInstance()
				.getSuggestions(mot, 5);
	}
	
}
