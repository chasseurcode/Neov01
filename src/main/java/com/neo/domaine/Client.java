package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Client extends Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private String nom;
	@Field
	private String adresse;
	@Field
	private String raisonSociale;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<Reglement> reglements=new ArrayList<Reglement>();
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<Campagne> campagnes=new ArrayList<Campagne>();
	
	public Client() {
		super();
	}
	
	/**
	 * 
	 * Getters and Setters
	 */
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public List<Reglement> getReglements() {
		return reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
