package com.neo.domaine;

import java.util.Date;

public class Domaine {
	
	private int id;
	private String ibelle;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	
	public Domaine() {
		
	}

/**
 * 
 * Getters and Setters
 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIbelle() {
		return ibelle;
	}


	public void setIbelle(String ibelle) {
		this.ibelle = ibelle;
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
	
	

}
