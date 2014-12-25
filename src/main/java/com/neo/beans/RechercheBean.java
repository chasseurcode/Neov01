package com.neo.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.domaine.Client;

@ManagedBean
@SessionScoped
public class RechercheBean {
	private String requete;
	private List<Client> clients;
	
	@PostConstruct
	public void init() {
		clients=new ArrayList<Client>();
	}

	public String getRequete() {
		return requete;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void search() {
		Map<String, List> resultat=Moteur.chercher(requete);
		for (Entry<String, List> mResultat : resultat.entrySet())
		{
		    if(mResultat.getKey().equalsIgnoreCase(Client.class.getName())){
		    	System.out.println("recherche client :"+mResultat.getValue().size());
		    	setClients(mResultat.getValue());
		    }
		}
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
}
