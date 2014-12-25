package com.neo.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Moteur {
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, List> chercher(String requete) {
		
		NeoSearch clientSearch=new ClientSearch();
		NeoSearch campSearch=new CampagneSearch();
		NeoSearch pubSearch=new PubSearch();
		
		clientSearch.setSuivant(campSearch);
		campSearch.setSuivant(pubSearch);
		return clientSearch.requete(requete, new HashMap<String, List>());
	}
}
