package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.TarifDAO;
import com.neo.domaine.Tarif;
import com.neo.utility.HibernateUtil;

public class TarifDaoImpl implements TarifDAO{

	@Override
	public void creer(Tarif tarif) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(tarif);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void modifier(Tarif tarif) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(tarif);
		session.getTransaction().commit();
		session.close();
	}


	@Override
	public void supprimer(Tarif tarif) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Tarif findById(int id) {
		Session session=HibernateUtil.getSession();
		return (Tarif) session.get(Tarif.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarif> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Tarif").list();
	}


}
