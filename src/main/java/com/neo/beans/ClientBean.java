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

import com.neo.dao.CampagneDAO;
import com.neo.dao.ClientDAO;
import com.neo.dao.ReglementDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.daoImpl.ReglementDaoImpl;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Reglement;
import com.neo.domaine.Role;
import com.neo.utility.Generateur;
import com.neo.utility.Generateur.Mode;

@ManagedBean
@SessionScoped
public class ClientBean {
	private Client client;
	private String idClient;
	private List<Client> listClient;
	private List<Reglement> reglements;
	private List<Campagne> campagnes;
	private String compte,motDePasse;
	private ClientDAO clientDAO;
	private CampagneDAO campDAO;
	private ReglementDAO regDAO;
	
	@PostConstruct
	public void init() {
		client=new Client();
		clientDAO=new ClientDaoImpl();
		campDAO=new CampagneDaoimpl();
		regDAO=new ReglementDaoImpl();
		setListClient(clientDAO.lister());
	}
	
	public ClientBean() {

	}

	public void load() {
		client=clientDAO.findById(new Long(idClient));
		setCampagnes(campDAO.lister(client.getId()));
		setReglements(regDAO.lister(client.getId()));
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
			setListClient(clientDAO.lister());
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

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public List<Reglement> getReglements() {
		return reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public List<Campagne> getCampagnes() {
		return campagnes;
	}

	public void setCampagnes(List<Campagne> campagnes) {
		this.campagnes = campagnes;
	}


}
