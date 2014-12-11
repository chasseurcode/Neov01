package com.neo.daoImpl;

import org.hibernate.Session;

import com.neo.dao.VueDao;
import com.neo.domaine.Vue;
import com.neo.utility.HibernateUtil;

public class VueDaoImpl  implements VueDao{

	@Override
	public void creer(Vue vue) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(vue);
		session.getTransaction().commit();
		session.close();
	}
	

}
