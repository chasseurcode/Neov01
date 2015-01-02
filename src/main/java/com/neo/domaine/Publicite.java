package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Resolution;

@Entity

public  abstract class Publicite extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private String intitule;
	@Field
	@DateBridge(resolution=Resolution.DAY)
	private Date dateDebut;
	@Field
	@DateBridge(resolution=Resolution.DAY)
	private Date dateFin;
	private long nbreVue;
	@ManyToOne(cascade=CascadeType.ALL)
	private Campagne campagne;
	@ManyToOne(cascade=CascadeType.ALL)
	private Tarif tarif;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Domaine> domaines=new ArrayList<Domaine>();
	
	public Publicite() {
		
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


	public long getNbreVue() {
		return nbreVue;
	}


	public void setNbreVue(long nbreVue) {
		this.nbreVue = nbreVue;
	}

	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
	
	public double getTotal() {
		return nbreVue*tarif.getTarifclient();
	}
	
	public void addDomaine(Domaine domaine){
		domaines.add(domaine);
	}

	public List<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}
	
	
}
