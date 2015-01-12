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
public class Reglement extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private Date dateReglement;
	@Field
	private String type;
	@Field(analyze=Analyze.NO)
	private float montant;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Campagne campagne;	
	
	public Reglement() {
		
	}

/**
 * 
 * Getters and Setters
 */
	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}
	
}
