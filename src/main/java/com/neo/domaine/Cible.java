package com.neo.domaine;

import java.util.Date;

public class Cible {

	private int id;
	private String ageMin;
	private String ageMax;
	private String ville;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	public Cible() {
		
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

	public String getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}

	public String getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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
