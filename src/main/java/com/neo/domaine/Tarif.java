package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public abstract class Tarif extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float tarifclient;
	private float tarifabonne;
	private boolean enVigueur=false;
	
	public Tarif() {
		
	}

	/**
	 * 
	 * Getters and Setters
	 */
	
	public boolean isEnVigueur() {
		return enVigueur;
	}

	public void setEnVigueur(boolean enVigueur) {
		this.enVigueur = enVigueur;
	}

	public float getTarifclient() {
		return tarifclient;
	}

	public void setTarifclient(float tarifclient) {
		this.tarifclient = tarifclient;
	}

	public float getTarifabonne() {
		return tarifabonne;
	}

	public void setTarifabonne(float tarifabonne) {
		this.tarifabonne = tarifabonne;
	}
	
}
