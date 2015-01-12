package com.neo.dao;

import java.util.List;

import com.neo.domaine.Abonne;
import com.neo.domaine.Vue;

public interface VueDao {

	public void creer(Vue vue);
	public Double getTotalgain(Abonne abonne);
	public void updateVue();
	public List<Vue> lister(Long id);
}
