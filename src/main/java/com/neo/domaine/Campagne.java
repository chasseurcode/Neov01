package com.neo.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

@Entity
@Indexed
public class Campagne extends Model {
	@Field
	private String intitule;
	@Field
	@DateBridge(resolution=Resolution.DAY)
	private Date dateDebut;
	@Field
	@DateBridge(resolution=Resolution.DAY)
	private Date dateFin;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Reglement> reglements=new ArrayList<Reglement>();
	@ManyToOne(cascade=CascadeType.ALL)
	private Client client;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Publicite> publicites=new ArrayList<Publicite>();

	public Campagne() {

	}

	/**
	 * Getters and Setters
	 */

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<Reglement> getReglements() {
		return reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public List<Publicite> getPublicites() {
		return publicites;
	}

	public void setPublicites(List<Publicite> publicites) {
		this.publicites = publicites;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addPublicite(Publicite publicite) {
		publicites.add(publicite);
	}

	public void removePublicite(Publicite publicite) {
		publicites.remove(publicite);
	}

	public void addReglement(Reglement reglement) {
		reglements.add(reglement);
	}

	public void removeReglement(Reglement reglement) {
		reglements.remove(reglement);
	}

	public int nombreDeVues(){
		int somme=0;
		for(Publicite p: publicites){
			for(Vue v:p.getVues()){
				somme=somme+v.getNbreVue();
			}	
		}
		return somme;
	}
	public long nombreDeVueTotal(){
		long somme=0;
		for(Publicite p:publicites){
			somme=somme+p.getNbreVue();
		}
		return somme;
	}

	public double totalReglement() {
		double somme=0;
		for(Reglement r: reglements){
			somme=somme+r.getMontant();
		}
		return somme;
	}

	public double resteAPayer() {
		return getTotal()-totalReglement();
	}


	public double getTotal() {
		double somme=0;
		for(Publicite p: publicites){
			if(p instanceof Textuelle){
				somme=somme+p.getTotal();
			}
			if(p instanceof Banniere){
				if(((Banniere)p).getNbreAppel()!=0 || ((Banniere)p).getNbreNotification()!=0 ){
					somme=somme+((((Banniere)p).getNbreAppel()*p.getTarif().getTarifclient())+
							((Banniere)p).getTotale());
				}
				else{
					somme=somme+p.getTotal();
				}
			}
		}
		return somme;
	}


}
