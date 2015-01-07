package com.neo.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.neo.dao.AbonneeDAO;
import com.neo.dao.PaiementDAO;
import com.neo.dao.SeuilDAO;
import com.neo.daoImpl.AbonneDAOImpl;
import com.neo.daoImpl.PaiementDaoImpl;
import com.neo.daoImpl.SeuilDAOImpl;
import com.neo.domaine.Abonne;
import com.neo.domaine.Paiement;
import com.neo.domaine.Seuil;

@ManagedBean
@RequestScoped
public class PaiementBean {
	private String idAbonne;
	private Paiement paiement;
	private PaiementDAO paiementDAO;
	private List<Paiement> paiements,AllPaiements;
	private AbonneeDAO abonneeDAO;
	private Abonne abonne;
	private Seuil seuil;
	private SeuilDAO seuilDAO;
	private List<Seuil> seuils;

	public void load() {
		abonne=abonneeDAO.findById(new Long(idAbonne));
		setPaiements(paiementDAO.lister(abonne.getId()));
	}

	public PaiementBean() {
		setPaiementDAO(new PaiementDaoImpl());
		setAbonneeDAO(new AbonneDAOImpl());
		setSeuilDAO(new SeuilDAOImpl());
		paiement=new Paiement();
		seuil=new Seuil();
		setAllPaiements(paiementDAO.lister());
		setSeuils(seuilDAO.lister());
	}


	public void addPaiement(){
		paiement.setAbonne(abonne);
		paiementDAO.creer(paiement);
		setPaiements(paiementDAO.lister(abonne.getId()));
		setAllPaiements(paiementDAO.lister());
	}

	public void addSeuil(){
		seuilDAO.updateAll();
		seuilDAO.creer(seuil);
		seuil=new Seuil();
		setSeuils(seuilDAO.lister());
	}
	



	public Paiement getPaiement() {
		return paiement;
	}
	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}
	public PaiementDAO getPaiementDAO() {
		return paiementDAO;
	}
	public void setPaiementDAO(PaiementDAO paiementDAO) {
		this.paiementDAO = paiementDAO;
	}
	public List<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}


	public AbonneeDAO getAbonneeDAO() {
		return abonneeDAO;
	}


	public void setAbonneeDAO(AbonneeDAO abonneeDAO) {
		this.abonneeDAO = abonneeDAO;
	}


	public Abonne getAbonne() {
		return abonne;
	}


	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}

	public String getIdAbonne() {
		return idAbonne;
	}

	public void setIdAbonne(String idAbonne) {
		this.idAbonne = idAbonne;
	}

	public List<Paiement> getAllPaiements() {
		return AllPaiements;
	}

	public void setAllPaiements(List<Paiement> allPaiements) {
		AllPaiements = allPaiements;
	}

	public Seuil getSeuil() {
		return seuil;
	}

	public void setSeuil(Seuil seuil) {
		this.seuil = seuil;
	}

	public SeuilDAO getSeuilDAO() {
		return seuilDAO;
	}

	public void setSeuilDAO(SeuilDAO seuilDAO) {
		this.seuilDAO = seuilDAO;
	}

	public List<Seuil> getSeuils() {
		return seuils;
	}

	public void setSeuils(List<Seuil> seuils) {
		this.seuils = seuils;
	}
	

}
