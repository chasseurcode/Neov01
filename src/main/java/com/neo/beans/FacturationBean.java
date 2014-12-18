package com.neo.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Campagne;

@ManagedBean
@SessionScoped
public class FacturationBean {
	private Campagne campagne;
	private CampagneDAO cmpDAO;
	
	public FacturationBean() {
		setCmpDAO(new CampagneDaoimpl());
		campagne=cmpDAO.findById(1);
	}
	
	public void createFacture() {
		
	}
	
	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	public CampagneDAO getCmpDAO() {
		return cmpDAO;
	}

	public void setCmpDAO(CampagneDAO cmpDAO) {
		this.cmpDAO = cmpDAO;
	}
}
