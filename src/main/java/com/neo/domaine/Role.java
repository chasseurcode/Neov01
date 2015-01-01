package com.neo.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Role extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Permission> permissions=new ArrayList<Permission>();
	@ManyToMany(mappedBy="roles")
	private List<Utilisateur> utilisateur;
	public Role() {

	}

	public Role(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void addPermission(Permission permission) {
		permissions.add(permission);
	}
	
	public void removePermission(Permission permission) {
		permissions.remove(permission);
	}

	public List<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(List<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
