package com.neo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Campagne;
import com.neo.domaine.Publicite;

@ManagedBean
@RequestScoped
public class CampagneBean {

	private boolean showPub=false;
	private Part fichier;
	private CampagneDAO campagneDAO;
	private Campagne campagne;
	private Publicite publicite;
	private List<Publicite> lesPublicites;
	private List<Campagne> lesCampagnes;
	private List<Campagne> lesCampagnesEncours, lesCampagnesEnAttentes,lesCampagnesterminees;


	public CampagneBean() {
		campagne=new Campagne();
		campagneDAO=new CampagneDaoimpl();
		publicite=new Publicite();
		lesPublicites=new ArrayList<Publicite>();
		lesCampagnesEnAttentes=new ArrayList<Campagne>();
		lesCampagnesEncours=new ArrayList<Campagne>();
		lesCampagnesterminees=new ArrayList<Campagne>();
		setLesCampagnes(campagneDAO.lister());
		initListe();
	}


	// affichage du formulaire des pubs
	public String afficherPub() {

		if(showPub){
			setShowPub(false);
		}
		else{
			setShowPub(true);
		}
		System.out.println("Essaiu");
		return null;
	}

	//ajout de la publicite
	public void addPublicite(){
		System.out.println("ds addpub");

	}

	//ajout de la campagne
	public void addCampagne(){
		System.out.println("ds add campagne");
	}

	// ajout ds les differentes liste de campagne
	private void initListe(){
		for(Campagne c:lesCampagnes){
			if((c.getDateFin().compareTo(new Date())<0) && (c.getReglements().size()>0) ){
				lesCampagnesterminees.add(c);
			}
			if((c.getDateFin().compareTo(new Date()) >0) && (c.getReglements().size()>0)){
				lesCampagnesEncours.add(c);
			}
			if((c.getDateFin().compareTo(new Date()) >0) && (c.getReglements().size()==0)){
				lesCampagnesEnAttentes.add(c);
			}
		}
	}


	/**
	 * 
	 * Getters et Setters
	 */

	public boolean isShowPub() {
		return showPub;
	}

	public void setShowPub(boolean showPub) {
		this.showPub = showPub;
	}

	public CampagneDAO getCampagneDAO() {
		return campagneDAO;
	}

	public void setCampagneDAO(CampagneDAO campagneDAO) {
		this.campagneDAO = campagneDAO;
	}

	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	public Publicite getPublicite() {
		return publicite;
	}

	public void setPublicite(Publicite publicite) {
		this.publicite = publicite;
	}

	public List<Publicite> getLesPublicites() {
		return lesPublicites;
	}

	public void setLesPublicites(List<Publicite> lesPublicites) {
		this.lesPublicites = lesPublicites;
	}


	public Part getFichier() {
		return fichier;
	}


	public void setFichier(Part fichier) {
		this.fichier = fichier;
	}


	public List<Campagne> getLesCampagnes() {
		return lesCampagnes;
	}


	public void setLesCampagnes(List<Campagne> lesCampagnes) {
		this.lesCampagnes = lesCampagnes;
	}


	public List<Campagne> getLesCampagnesEncours() {
		return lesCampagnesEncours;
	}


	public void setLesCampagnesEncours(List<Campagne> lesCampagnesEncours) {
		this.lesCampagnesEncours = lesCampagnesEncours;
	}


	public List<Campagne> getLesCampagnesEnAttentes() {
		return lesCampagnesEnAttentes;
	}


	public void setLesCampagnesEnAttentes(List<Campagne> lesCampagnesEnAttentes) {
		this.lesCampagnesEnAttentes = lesCampagnesEnAttentes;
	}


	public List<Campagne> getLesCampagnesterminees() {
		return lesCampagnesterminees;
	}


	public void setLesCampagnesterminees(List<Campagne> lesCampagnesterminees) {
		this.lesCampagnesterminees = lesCampagnesterminees;
	}





}
