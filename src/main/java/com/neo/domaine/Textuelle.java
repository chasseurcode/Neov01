package com.neo.domaine;

import javax.persistence.Entity;

@Entity
public class Textuelle extends Publicite{

	private String contenu;

	public Textuelle() {
		super();
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	 
	
}
