package com.neo.domaine;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Depense extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private String intitule;
	@Field
	private String fournisseur;
	@Field
	private Date date;
	private float montant;
	
	public Depense() {
		
	}

/**
 * 
 * Getters and Setters
 */
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

	
	@Override
	public String toString() {
		return "["+getIntitule()+"]-["+getFournisseur()+"]["+getMontant()+"]["+getDate()+"]";
	}
	

}
