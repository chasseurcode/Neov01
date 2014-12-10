package com.neo.domaine;

import java.util.Date;

public class Carte {

	private int id;
	private String operateur;
	private String numero;
	private float montant;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	
	public Carte() {
		
	}

/***
 * 
 * Getters and Setters
 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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
