package com.neo.dao;

import com.neo.domaine.Abonne;
import com.neo.domaine.Vue;

public interface VueDao {

	public void creer(Vue vue);
	public Double getTotalgain(Abonne abonne);
	public void updateVue();
}
