package com.neo.domaine;

import javax.persistence.Entity;

@Entity
public class Banniere extends Publicite{

	private String image;
	private boolean parAppel;
	private boolean parNotification;
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

	public boolean isParAppel() {
		return parAppel;
	}

	public void setParAppel(boolean parAppel) {
		this.parAppel = parAppel;
	}

	public boolean isParNotification() {
		return parNotification;
	}

	public void setParNotification(boolean parNotification) {
		this.parNotification = parNotification;
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
	
	
}
