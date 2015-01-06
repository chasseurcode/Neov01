package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Seuil extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int seuil;
	private boolean enVigueur=true;
	
	
	public int getSeuil() {
		return seuil;
	}
 	
	
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
	public boolean isEnVigueur() {
		return enVigueur;
	}
	public void setEnVigueur(boolean enVigueur) {
		this.enVigueur = enVigueur;
	}
	
	

}
