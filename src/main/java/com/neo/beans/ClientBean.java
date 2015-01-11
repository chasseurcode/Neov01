package com.neo.beans;

import java.util.ArrayList;
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
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Facture;
import com.neo.domaine.Publicite;
import com.neo.domaine.Reglement;
import com.neo.domaine.Role;
import com.neo.domaine.Textuelle;
import com.neo.utility.Generateur;
import com.neo.utility.Generateur.Mode;
import com.neo.utility.SendMail;

@ManagedBean
@SessionScoped
public class ClientBean {
	private Client client,current;
	private String idClient;
	private Long idCamp;
	private List<Client> listClient;
	private List<Reglement> LesReglements;
	private List<Campagne> campagnes,camps,campsEncours;
	private List<Publicite> pubTextuelle,pubBaniere;
	private String compte,motDePasse;
	private ClientDAO clientDAO;
	private CampagneDAO campDAO;
	private List<Facture>factures;

	@PostConstruct
	public void init() {
		client=new Client();
		clientDAO=new ClientDaoImpl();
		campDAO=new CampagneDaoimpl();
		setListClient(clientDAO.lister());
		current=new Client();
		current=clientDAO.findByCompte("neone1");
		setCamps(campDAO.lister(current.getId()));
		setCampsEncours(campDAO.listerEncours(current.getId()));
	}

	public ClientBean() {

	}

	//chargement de la fiche client
	public void load() {
		client=clientDAO.findById(new Long(idClient));
		setCampagnes(campDAO.lister(client.getId()));
		listereg();
	}

	//liste des reglemetns de la fiche client
	private void listereg(){
		LesReglements=new ArrayList<Reglement>();
		for(Campagne camp: campagnes){
			LesReglements.addAll(camp.getReglements());
		}
	}

	//load publicite pour detail
	public String loadPubs(Campagne campagne){
		pubBaniere=new ArrayList<Publicite>();
		pubTextuelle=new ArrayList<Publicite>();
		if(campagne!=null){
			for(Publicite p:campagne.getPublicites()){
				if(p instanceof Banniere){
					pubBaniere.add(p);
				}
				if(p instanceof Textuelle){
					pubTextuelle.add(p);
				}
			}
			return "pretty:detailPubs";
		}
		return null;
	}


	//load facture du client
	public void loadFactures(){
		Campagne c=campDAO.findById(idCamp);
		setFactures(campDAO.listerFacture(c.getId()));
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

	public List<Reglement> getLesReglements() {
		return LesReglements;
	}

	public void setLesReglements(List<Reglement> lesReglements) {
		LesReglements = lesReglements;
	}

	public List<Campagne> getCampagnes() {
		return campagnes;
	}

	public void setCampagnes(List<Campagne> campagnes) {
		this.campagnes = campagnes;
	}

	public List<Campagne> getCamps() {
		return camps;
	}

	public void setCamps(List<Campagne> camps) {
		this.camps = camps;
	}

	public List<Campagne> getCampsEncours() {
		return campsEncours;
	}

	public void setCampsEncours(List<Campagne> campsEncours) {
		this.campsEncours = campsEncours;
	}

	public List<Publicite> getPubTextuelle() {
		return pubTextuelle;
	}

	public void setPubTextuelle(List<Publicite> pubTextuelle) {
		this.pubTextuelle = pubTextuelle;
	}

	public List<Publicite> getPubBaniere() {
		return pubBaniere;
	}

	public void setPubBaniere(List<Publicite> pubBaniere) {
		this.pubBaniere = pubBaniere;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Long getIdCamp() {
		return idCamp;
	}

	public void setIdCamp(Long idCamp) {
		this.idCamp = idCamp;
	}


}
