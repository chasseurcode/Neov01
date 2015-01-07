package com.neo.domaine;

import javax.persistence.Entity;

@Entity
public class Cible extends Model{
	private String ageMin;
	private String ageMax;
	private String sexe;
	
	public Cible() {
		
	}
	
/**
 * 
 * Getters and Setters
 */

	public String getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}

	public String getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	
}
