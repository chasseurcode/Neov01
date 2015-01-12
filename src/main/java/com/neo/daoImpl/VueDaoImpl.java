package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.VueDao;
import com.neo.domaine.Abonne;
import com.neo.domaine.Vue;
import com.neo.utility.HibernateUtil;

public class VueDaoImpl  implements VueDao{

	@Override
	public void creer(Vue vue) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(vue);
		session.flush();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getTotalgain(Abonne abonne) {
		Session session=HibernateUtil.getSession();
		List<Vue> list= session.createQuery("SELECT v FROM Vue v "
				+ "where v.regler = :etat and v.abonne= :userid")
				.setBoolean("etat", false)
				.setLong("userid", new Long(abonne.getId()))
				.list();
		Double somme=0.0;
		for(Vue v: list){
			somme=somme+v.getGain();
		}
		return somme;
	}

	@Override
	public void updateVue() {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.createQuery("UPDATE Vue tn SET tn.regler = :etat ")
			   .setBoolean("etat", true).executeUpdate();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vue> lister(Long id) {
		Session session=HibernateUtil.getSession();
		return session.createQuery("From Vue v where v.publicite.id = :pub ").setLong("pub", id).list();
	}



}
