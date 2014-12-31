package com.neo.domaine;

import javax.persistence.Entity;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Textuelle extends Publicite{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
