package com.neo.domaine;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Paiement extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field
	private Date date;
	@Field(analyze=Analyze.NO)
	private double montant;
	@ManyToOne
	private Abonne abonne;
	
	public Paiement() {
		
	}

/**
 * 
 * Getters and Setters
 */
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}
	
	
}
