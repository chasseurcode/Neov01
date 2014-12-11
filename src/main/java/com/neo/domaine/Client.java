package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adresse;
	private String raisonSociale;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Reglement> reglements=new ArrayList<Reglement>();
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * Getters and Setters
	 */
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public List<Reglement> getReglements() {
		return reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}
	
	

}
