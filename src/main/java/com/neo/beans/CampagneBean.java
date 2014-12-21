package com.neo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import com.neo.dao.CampagneDAO;
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


	public CampagneBean() {
		campagne=new Campagne();
		publicite=new Publicite();
		lesPublicites=new ArrayList<Publicite>();
		lesCampagnes=new ArrayList<Campagne>();
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





}
