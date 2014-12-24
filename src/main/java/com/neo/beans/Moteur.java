package com.neo.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.neo.domaine.Campagne;
import com.neo.domaine.Client;

public class Moteur {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		Search clientSearch=new ClientSearch();
		Search campSearch=new CampagneSearch();
		
		clientSearch.setSuivant(campSearch);
		Map<String, List> result=clientSearch.requete("", new HashMap<String, List>());

		for (Entry<String, List> mResultat : result.entrySet())
		{
		    
		    if(mResultat.getKey().equalsIgnoreCase(Client.class.getName())){
		    	System.out.println("\nListe des clients :");
		    	List<Client> listR=(List<Client>)mResultat.getValue();
		    	for(Client c: listR){
		    		System.out.println(c.getCompte());
		    	}
		    }
		    
		    if(mResultat.getKey().equalsIgnoreCase(Campagne.class.getName())){
		    	List<Campagne> listR=(List<Campagne>)mResultat.getValue();
		    	System.out.println("\nListe des campagnes :");
		    	for(Campagne c: listR){
		    		System.out.println(c.getIntitule());
		    	}
		    }
		    
		}
		
	}
}
