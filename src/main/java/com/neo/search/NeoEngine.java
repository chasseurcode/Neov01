package com.neo.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeoEngine {
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, List> chercher(String requete) {
		
		NeoSearch clientSearch=new ClientSearch();
		NeoSearch campSearch=new CampagneSearch();
		NeoSearch pubSearch=new PubSearch();
		NeoSearch abonneSearch=new AbonneSearch(); 
		
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
