package com.neo.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Facture;

@ManagedBean
@SessionScoped
public class FacturationBean {
	private Facture facture;
	private CampagneDAO cmpDAO;
	
	public FacturationBean() {
		setCmpDAO(new CampagneDaoimpl());
		facture=cmpDAO.findLastRecord();
	}
	
	public void createFacture() {
		
	}
	

	public CampagneDAO getCmpDAO() {
		return cmpDAO;
	}

	public void setCmpDAO(CampagneDAO cmpDAO) {
		this.cmpDAO = cmpDAO;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
}
