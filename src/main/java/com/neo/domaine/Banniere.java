package com.neo.domaine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@XmlRootElement
public class Banniere extends Publicite{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String image;
	@ManyToOne(cascade=CascadeType.ALL)
	private TarifNotification tarifNotification;
	private int nbreAppel;
	private int nbreNotification;
	
	public Banniere() {
	
	}

/**
 * 
 * Getters and Setters
 */ 
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNbreAppel() {
		return nbreAppel;
	}

	public void setNbreAppel(int nbreAppel) {
		this.nbreAppel = nbreAppel;
	}

	public int getNbreNotification() {
		return nbreNotification;
	}

	public void setNbreNotification(int nbreNotification) {
		this.nbreNotification = nbreNotification;
	}

	public Tarif getTarifNotification() {
		return tarifNotification;
	}

	public void setTarifNotification(TarifNotification tarifNotification) {
		this.tarifNotification = tarifNotification;
	}

	public double getTotale() {
		return nbreNotification*tarifNotification.getTarifclient();
	}
	
}
