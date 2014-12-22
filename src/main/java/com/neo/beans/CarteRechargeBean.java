package com.neo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.dao.CarteDAO;
import com.neo.daoImpl.CarteDaoImpl;
import com.neo.domaine.Carte;

@ManagedBean
@SessionScoped
public class CarteRechargeBean {

	private Carte carteRecharge,current;
	private CarteDAO carteDAO;
	private List<Carte> lesCartes;




	public CarteRechargeBean () {
		carteRecharge=new Carte();
		carteDAO=new CarteDaoImpl();
		lesCartes=new ArrayList<Carte>();
		rafraichirListe();
	}

	// ajouter carte
	public String addCarte(){
		if(current==null){
			carteDAO.creer(carteRecharge);
			carteRecharge=new Carte();
		}
		else{
			carteDAO.modifier(current);
			carteRecharge=new Carte();
		}
		rafraichirListe();
		return null;
	}

	//modification de la carte
	public void edition(Carte carte) {
		setCarteRecharge(carte);
		setCurrent(carteRecharge);
	}

	//raffraichir la liste des cartes
	private  void rafraichirListe(){
		lesCartes=carteDAO.lister();
	}


	/**
	 * 
	 * Getters et Setters
	 */

	public CarteDAO getCarteDAO() {
		return carteDAO;
	}

	public Carte getCarteRecharge() {
		return carteRecharge;
	}

	public void setCarteRecharge(Carte carteRecharge) {
		this.carteRecharge = carteRecharge;
	}

	public void setCarteDAO(CarteDAO carteDAO) {
		this.carteDAO = carteDAO;
	}

	public List<Carte> getLesCartes() {
		return lesCartes;
	}

	public void setLesCartes(List<Carte> lesCartes) {
		this.lesCartes = lesCartes;
	}

	public Carte getCurrent() {
		return current;
	}

	public void setCurrent(Carte current) {
		this.current = current;
	}



}
