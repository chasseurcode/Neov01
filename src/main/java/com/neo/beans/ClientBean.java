package com.neo.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.neo.beans.Generateur.Mode;
import com.neo.dao.ClientDAO;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.domaine.Client;
import com.neo.domaine.Role;

@ManagedBean
@SessionScoped
public class ClientBean {
	private Client client;
	private List<Client> listClient;
	private String compte,motDePasse;
	private ClientDAO clientDAO;
	
	@PostConstruct
	public void init() {
		client=new Client();
		clientDAO=new ClientDaoImpl();
		setListClient(clientDAO.lister());
	}
	
	public ClientBean() {

	}

	public String saveClient() {
		//génération du compte client
		generateAccount();
		//Envoi des info compte au client
		try {
			SendMail.envoyer(client.getEmail(), client.getCompte(), motDePasse);
			client.addRole(new Role("Client"));
			clientDAO.creer(client);
			client=new Client();
			return "pretty:listeclient";
		} catch (AddressException e) {
			System.out.println("Problem de mail");
		} catch (MessagingException e) {
			System.out.println("Message non envoyé");
		}
		return null;
	}

	/*
	 * Generation du compte
	 */
	private void generateAccount() {
		try {
			compte = "NEONE"+Generateur.generateRandomString(5, Mode.ALPHANUMERIC).toUpperCase();
			motDePasse=Generateur.generateRandomString(8, Mode.ALPHANUMERIC);
			RandomNumberGenerator rng = new SecureRandomNumberGenerator();
			Object salt = rng.nextBytes();
			String hashPass = new Sha256Hash(motDePasse, salt,1024).toBase64();
			client.setCompte(compte);
			client.setMotDePasse(hashPass);
			client.setSaltMotDePasse(salt.toString());
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public String editClient(Client client) {
		setClient(client);
		System.out.println("oui");
		return "pretty:modifclient";
	}
	
	public String update() {
		clientDAO.modifier(client);
		return "pretty:listeclient";
	}
	
	
	
	/*
	 * 
	 * Getter and Setter
	 */
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getListClient() {
		return listClient;
	}

	public void setListClient(List<Client> listClient) {
		this.listClient = listClient;
	}


}
