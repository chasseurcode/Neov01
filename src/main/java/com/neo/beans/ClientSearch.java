package com.neo.beans;

import java.util.ArrayList;
import java.util.List;

import com.neo.domaine.Client;



public class ClientSearch extends Search {
	
	public ClientSearch() {
		setNomEntite(Client.class.getName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List chercher(String req) {
		Client c1=new Client();
		c1.setCompte("Bongo");
		Client c2=new Client();
		c2.setCompte("Bongo");
		Client c3=new Client();
		c3.setCompte("Bongo");
		List<Object> clients=new ArrayList<Object>();
		clients.add(c1);
		clients.add(c2);
		clients.add(c3);
		return clients;
	}

	
}
