package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Message extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String objet;
	private String corps;
	private boolean vue=false;
	@ManyToOne
	private Utilisateur utilisateur;
	
	public Message() {
		super();
	}

	/***
	 * 
	 * Getters and Setters
	 */

	public String getObjet() {
		return objet;
	}


	public void setObjet(String objet) {
		this.objet = objet;
	}


	public String getCorps() {
		return corps;
	}


	public void setCorps(String corps) {
		this.corps = corps;
	}


	public boolean isVue() {
		return vue;
	}


	public void setVue(boolean vue) {
		this.vue = vue;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}
