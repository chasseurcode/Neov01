package com.neo.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Campagne {

	@Id @GeneratedValue
	private long id;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Reglement> reglements=new ArrayList<Reglement>();
	@ManyToOne(cascade=CascadeType.ALL)
	private Client client;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Publicite> publicites=new ArrayList<Publicite>();
	private Date dateCreation=new Date();
	private Date dateMaj=new Date();
	private boolean supprimer=false;


	public Campagne() {

	}

	/**
	 * Getters and Setters
	 */

	public String getIntitule() {
		return intitule;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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

	public List<Reglement> getReglements() {
		return reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public List<Publicite> getPublicites() {
		return publicites;
	}

	public void setPublicites(List<Publicite> publicites) {
		this.publicites = publicites;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addPublicite(Publicite publicite) {
		publicites.add(publicite);
	}

	public void removePublicite(Publicite publicite) {
		publicites.remove(publicite);
	}

	public double getTotal() {
		double somme=0;
		for(Publicite p: publicites){
			somme=somme+p.getTotal();
		}
		return somme;
	}
}