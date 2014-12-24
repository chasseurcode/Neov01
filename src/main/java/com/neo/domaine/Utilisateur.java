package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilisateur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	@Field
	private String compte;
	private String motDePasse;
	@Field
	private String email;
	@Field
	private String telehone;
	private String saltMotDePasse;
	private boolean actif=true;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<Role> roles=new ArrayList<Role>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<Message> messages=new ArrayList<Message>();
	private Date creation=new Date();
	private Date miseAJour=new Date();
	private boolean supprimer=false;
	
	
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
	public String getTelehone() {
		return telehone;
	}
	public void setTelehone(String telehone) {
		this.telehone = telehone;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Date getMiseAJour() {
		return miseAJour;
	}
	public void setMiseAJour(Date miseAJour) {
		this.miseAJour = miseAJour;
	}
	public boolean isSupprimer() {
		return supprimer;
	}
	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
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
	
}
