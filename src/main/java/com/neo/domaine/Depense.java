package com.neo.domaine;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Depense {
	
	@Id @GeneratedValue
	private long id;
	@Field
	private String intitule;
	@Field
	private String fournisseur;
	@Field
	private Date date;
	private float montant;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	
	public Depense() {
		
	}

/**
 * 
 * Getters and Setters
 */
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getFournisseur() {
		return fournisseur;
	}


	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
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
	
	@Override
	public String toString() {
		return "["+getIntitule()+"]-["+getFournisseur()+"]["+getMontant()+"]["+getDate()+"]";
	}
	

}
