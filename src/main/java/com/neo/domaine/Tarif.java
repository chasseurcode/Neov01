package com.neo.domaine;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tarif {

	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	private float tarifclient;
	private float tarifabonne;
	private boolean enVigueur;
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;
	
	public Tarif() {
		
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

	
	public boolean isEnVigueur() {
		return enVigueur;
	}

	public void setEnVigueur(boolean enVigueur) {
		this.enVigueur = enVigueur;
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
