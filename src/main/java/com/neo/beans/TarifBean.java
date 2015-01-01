package com.neo.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.dao.TarifDAO;
import com.neo.daoImpl.TarifDaoImpl;
import com.neo.domaine.Tarif;
import com.neo.domaine.TarifAppel;
import com.neo.domaine.TarifNotification;
import com.neo.domaine.TarifTextuelle;

@ManagedBean
@SessionScoped
public class TarifBean {
	private double tclient,tabonn;
	private List<TarifAppel> tarifAppels;
	private List<TarifNotification> tarifNotifications;
	private List<TarifTextuelle> tarifTextuelles;
	private TarifDAO daoTarif;
	private String typeTarif;
	
	@PostConstruct
	public void init() {
		setDaoTarif(new TarifDaoImpl());
		refreshList();
	}

	private void refreshList() {
		setTarifAppels(daoTarif.listerTarifappel());
		setTarifNotifications(daoTarif.listerTarifNotif());
		setTarifTextuelles(daoTarif.listerTarifText());
	}

	public String addTarif() {

		System.out.println(typeTarif);
		if(typeTarif.equalsIgnoreCase("appel")){
			TarifAppel tarif=new TarifAppel();
			tarif.setTarifabonne((float) tabonn);
			tarif.setTarifclient((float) tclient);
			daoTarif.creer(tarif);
		}

		if(typeTarif.equalsIgnoreCase("notifier")){
			TarifNotification tarif=new TarifNotification();
			tarif.setTarifabonne((float) tabonn);
			tarif.setTarifclient((float) tclient);
			daoTarif.creer(tarif);
		}

		if(typeTarif.equalsIgnoreCase("textuelle")){
			TarifTextuelle tarif=new TarifTextuelle();
			tarif.setTarifabonne((float) tabonn);
			tarif.setTarifclient((float) tclient);
			daoTarif.creer(tarif);
		}
		setTabonn(0.0);
		setTclient(0.0);
		refreshList();
		return null;
	}
	
	public void miseEnVigueur(Tarif tarif,String type) {
		if(type.equalsIgnoreCase("app")){
			daoTarif.updateAllApp();
			TarifAppel appt=daoTarif.findTarifAppelById(tarif.getId());
			appt.setEnVigueur(true);
			System.out.println("id :"+appt.getId()+" etat :"+appt.isEnVigueur());
			daoTarif.modifier(appt);
		}else if (type.equalsIgnoreCase("text")) {
			daoTarif.updateAllText();
			TarifTextuelle textTar=daoTarif.findTarifTextuelleById(tarif.getId());
			textTar.setEnVigueur(true);
			System.out.println("id :"+textTar.getId()+" etat :"+textTar.isEnVigueur());
			daoTarif.modifier(textTar);
		}else if (type.equalsIgnoreCase("notif")) {
			daoTarif.updateAllNotif();
			TarifNotification Not=daoTarif.findNotificationById(tarif.getId());
			Not.setEnVigueur(true);
			daoTarif.modifier(Not);
		}
		
		daoTarif.modifier(tarif);
		System.out.println("modif tarif id: "+tarif.getId()+" type: "+type);
		refreshList();
	}

	/*
	 * 
	 * Getter and setter
	 */
	public double getTclient() {
		return tclient;
	}
	public void setTclient(double tclient) {
		this.tclient = tclient;
	}
	public double getTabonn() {
		return tabonn;
	}
	public void setTabonn(double tabonn) {
		this.tabonn = tabonn;
	}


	public String getTypeTarif() {
		return typeTarif;
	}


	public void setTypeTarif(String typeTarif) {
		this.typeTarif = typeTarif;
	}

	public TarifDAO getDaoTarif() {
		return daoTarif;
	}

	public void setDaoTarif(TarifDAO daoTarif) {
		this.daoTarif = daoTarif;
	}

	public List<TarifAppel> getTarifAppels() {
		return tarifAppels;
	}

	public void setTarifAppels(List<TarifAppel> tarifAppels) {
		this.tarifAppels = tarifAppels;
	}

	public List<TarifNotification> getTarifNotifications() {
		return tarifNotifications;
	}

	public void setTarifNotifications(List<TarifNotification> tarifNotifications) {
		this.tarifNotifications = tarifNotifications;
	}

	public List<TarifTextuelle> getTarifTextuelles() {
		return tarifTextuelles;
	}

	public void setTarifTextuelles(List<TarifTextuelle> tarifTextuelles) {
		this.tarifTextuelles = tarifTextuelles;
	}
}
