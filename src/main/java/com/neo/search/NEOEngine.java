package com.neo.search;

import java.util.HashMap;
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
		return clientSearch.requete(requete, new HashMap<String, List>());
	}
	
	public static String[] suggestion(String mot) {
		return SuggestionIndex.getInstance()
				.getSuggestions(mot, 5);
	}
	
}
