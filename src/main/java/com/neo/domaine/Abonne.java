package com.neo.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@XmlRootElement
@Indexed
public class Abonne extends Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private String prenom;
	private Date dateDeNaissance;
	@Field
	private String codeParrainege;
	@Field
	private String codeFilleule;
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
	
	public List<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}
	
	
}
