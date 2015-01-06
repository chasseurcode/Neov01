package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.SeuilDAO;
import com.neo.domaine.Seuil;
import com.neo.utility.HibernateUtil;

public class SeuilDAOImpl implements SeuilDAO{

	@Override
	public void creer(Seuil seuil) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(seuil);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Seuil seuil) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(seuil);
		session.getTransaction().commit();	
	}

	@Override
	public void supprimer(Seuil seuil) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seuil findById(Long id) {
		Session session=HibernateUtil.getSession();
		return (Seuil) session.get(Seuil.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seuil> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Seuil").list();
	}

}
