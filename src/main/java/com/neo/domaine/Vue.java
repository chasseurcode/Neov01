package com.neo.domaine;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Vue extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nbreVue;
	private float gain;
	private boolean regler=false;
	@ManyToOne
	private Abonne abonne;
	@ManyToOne
	private Publicite publicite;
	
	public Vue() {
		
	}

	/**
	 * 
	 * Getters and Setters
	 */

	public int getNbreVue() {
		return nbreVue;
	}

	public void setNbreVue(int nbreVue) {
		this.nbreVue = nbreVue;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}

	public Publicite getPublicite() {
		return publicite;
	}

	public void setPublicite(Publicite publicite) {
		this.publicite = publicite;
	}

	public boolean isRegler() {
		return regler;
	}

	public void setRegler(boolean regler) {
		this.regler = regler;
	}	

}
