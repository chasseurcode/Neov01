package com.neo.domaine;

import java.util.Date;

public class Campagne {

	private int id;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;


	public Campagne() {
		
	}

	/**
	 * Getters and Setters
	 */
	
	public String getIntitule() {
		return intitule;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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
