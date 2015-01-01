package com.neo.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.neo.dao.DepenseDAO;
import com.neo.daoImpl.DepenseDaoImpl;
import com.neo.domaine.Depense;

@ManagedBean
@RequestScoped
public class DepenseBean {
	private Depense depense;
	private DepenseDAO daoDep;
	private List<Depense> depenses;
	@PostConstruct
	public void init() {
		depense=new Depense();
		setDaoDep(new DepenseDaoImpl());
		setDepenses(daoDep.lister());
	}
	
	
	public String addDepence() {
		daoDep.creer(depense);
		depense=new Depense();
		setDepenses(daoDep.lister());
		return null;
	}
	
	public Depense getDepense() {
		return depense;
	}
	public void setDepense(Depense depense) {
		this.depense = depense;
	}
	public DepenseDAO getDaoDep() {
		return daoDep;
	}
	public void setDaoDep(DepenseDAO daoDep) {
		this.daoDep = daoDep;
	}


	public List<Depense> getDepenses() {
		return depenses;
	}


	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}
	
}
