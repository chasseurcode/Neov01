package com.neo.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Abonne extends Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prenom;
	private Date dateDeNaissance;
	private String codeParrainege;
	private String codeFilleule;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Paiement>paiements=new ArrayList<Paiement>();
	
	
	
	public Abonne() {
		
	}
	
	
	
	public Abonne(String prenom, Date dateDeNaissance, String codeParrainege,
			String codeFilleule) {
		super();
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.codeParrainege = codeParrainege;
		this.codeFilleule = codeFilleule;
	}



	/**
	 * 
	 * Getters and Setters
	 */
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getCodeParrainege() {
		return codeParrainege;
	}
	public void setCodeParrainege(String codeParrainege) {
		this.codeParrainege = codeParrainege;
	}
	public String getCodeFilleule() {
		return codeFilleule;
	}
	public void setCodeFilleule(String codeFilleule) {
		this.codeFilleule = codeFilleule;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateMaj() {
		return dateMaj;
	}
	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}
	public boolean getSupprimer() {
		return supprimer;
	}
	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
	}
	public List<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}
	
	
}
