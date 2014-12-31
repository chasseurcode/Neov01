package com.neo.domaine;

import javax.persistence.Entity;

@Entity
public class Carte extends Model {

	private String operateur;
	private String numero;
	private float montant;
	private boolean active=true;
	
	public Carte() {
		
	}

/***
 * 
 * Getters and Setters
 */

	public String getOperateur() {
		return operateur;
	}


	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
