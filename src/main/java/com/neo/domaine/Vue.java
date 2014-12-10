package com.neo.domaine;

import java.util.Date;

public class Vue {
	
	private int id;
	private int nbreVue;
	private float gain;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	public Vue() {
		
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

	public int getNbreVue() {
		return nbreVue;
	}

	public void setNbreVue(int nbreVue) {
		this.nbreVue = nbreVue;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
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
