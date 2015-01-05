package com.neo.domaine;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@XmlRootElement
public class Textuelle extends Publicite{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
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
