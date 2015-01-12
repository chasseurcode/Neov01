package com.neo.domaine;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Facture extends Model {
	
	@Field(analyze=Analyze.NO)
	private String numero;
	private Date date;
	@ManyToOne(cascade=CascadeType.ALL)
	private Campagne campagne;
	
	
	
	
	public Facture() {}
	
	
	
	/**
	 * 
	 * Getters and Setters
	 */
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Campagne getCampagne() {
		return campagne;
	}
	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}
	
	
	

}
