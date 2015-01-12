package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Entity
public class Utilisateur extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private String compte;
	private String motDePasse;
	@Field(analyze=Analyze.NO)
	private String email;
	@Field(analyze=Analyze.NO)
	private String telephone;
	private String saltMotDePasse;
	private boolean actif=true;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Role> roles=new ArrayList<Role>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Message> messages=new ArrayList<Message>();
	
	public Utilisateur() {
	
	}
	
	public Utilisateur(String compte, String motDePasse, String email,
			boolean actif) {
		super();
		this.compte = compte;
		this.motDePasse = motDePasse;
		this.email = email;
		this.actif = actif;
	}

	public String getCompte() {
		return compte;
	}
	public void setCompte(String compte) {
		this.compte = compte;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public String getSaltMotDePasse() {
		return saltMotDePasse;
	}

	public void setSaltMotDePasse(String saltMotDePasse) {
		this.saltMotDePasse = saltMotDePasse;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "["+getCompte()+"] - ["+getEmail()+"] - ["+getTelephone()+"] - ["+getTelephone()+"]";
	}
	
	public List<Message> getLastMessages() {
		List<Message> msgs=new ArrayList<Message>();
		System.out.println(this.messages.size());
		for(Message m: this.messages){
			System.out.println(m.getCorps()+" - "+m.getObjet()+" - "+m.getCreated()+" et "+this.getUpdated());
			if(this.getUpdated().before(m.getCreated())){
				System.out.println("trouver");
				msgs.add(m);
			}
		}
		return msgs;
	}
}
