package com.neo.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Moteur {
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, List> chercher(String requete) {
		
		NeoSearch clientSearch=new ClientSearch();
		NeoSearch campSearch=new CampagneSearch();
		
		clientSearch.setSuivant(campSearch);
		return clientSearch.requete(requete, new HashMap<String, List>());
//
//		for (Entry<String, List> mResultat : result.entrySet())
//		{
//		    
//		    if(mResultat.getKey().equalsIgnoreCase(Client.class.getName())){
//		    	System.out.println("\nListe des clients :");
//		    	List<Client> listR=(List<Client>)mResultat.getValue();
//		    	for(Client c: listR){
//		    		System.out.println(c.getCompte());
//		    	}
//		    }
//		    
//		    if(mResultat.getKey().equalsIgnoreCase(Campagne.class.getName())){
//		    	List<Campagne> listR=(List<Campagne>)mResultat.getValue();
//		    	System.out.println("\nListe des campagnes :");
//		    	for(Campagne c: listR){
//		    		System.out.println(c.getIntitule());
//		    	}
//		    }
//		    
//		}
		
	}
}
