package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.CampagneDAO;
import com.neo.domaine.Campagne;
import com.neo.utility.HibernateUtil;

public class CampagneDaoimpl implements CampagneDAO{

	@Override
	public void creer(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(campagne);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void modifier(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(campagne);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void supprimer(Campagne campagne) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Campagne findById(int id) {
		Session session=HibernateUtil.getSession();
		return (Campagne) session.get(Campagne.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Campagne> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Campagne c").list();
	}

	

	

}
