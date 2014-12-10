package com.neo.domaine;

import java.io.Serializable;

public class Client extends Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adresse;
	private String raisonSociale;
	
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
	
	

}
