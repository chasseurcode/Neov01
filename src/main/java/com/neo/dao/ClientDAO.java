package com.neo.dao;

import java.util.List;

import com.neo.domaine.Client;

public interface ClientDAO {

	public void creer(Client client);
	public void modifier(Client client);
	public void supprimer(Client client);
	public Client findById(Long long1);
	public Client findByCompte(String compte);
	public List<Client> lister();
	public Client findLastRecord();
}
