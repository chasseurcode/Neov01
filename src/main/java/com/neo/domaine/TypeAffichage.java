package com.neo.domaine;

import java.util.Date;

public class TypeAffichage {
	
	private int id;
	private String intitule;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	public TypeAffichage() {
		
	}

	/**
	 * 
	 * Getters and Setters@return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
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

	public boolean isSupprimer() {
		return supprimer;
	}

	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
	}
	
	
	

}
