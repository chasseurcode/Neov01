package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Permission extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	
	public Permission() {
	}

	public Permission(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		String[] perms=nom.split(":");
		return perms[0]+"-"+perms[1];
	}
	
}
