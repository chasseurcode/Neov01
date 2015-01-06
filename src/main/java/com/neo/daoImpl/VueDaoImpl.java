package com.neo.daoImpl;

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
		session.getTransaction().commit();
	}

	@Override
	public Double getTotalgain(Abonne abonne) {
		Session session=HibernateUtil.getSession();
		return (Double) session.createQuery("SELECT SUM(v.gain) From Vue as v where v.abonne.id =:ab")
				.setLong("ab", abonne.getId()).list().get(0);
	}
	

}
