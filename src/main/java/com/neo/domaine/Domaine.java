package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Domaine extends Model implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String libelle;	
	
	public Domaine() {
		
	}

/**
 * 
 * Getters and Setters
 */

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
