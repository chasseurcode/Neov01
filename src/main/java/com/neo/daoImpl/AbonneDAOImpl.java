package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.AbonneeDAO;
import com.neo.domaine.Abonne;
import com.neo.utility.HibernateUtil;

public class AbonneDAOImpl implements AbonneeDAO{

	@Override
	public void creer(Abonne abonne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(abonne);
		session.getTransaction().commit();
		
	}
	
	@Override
	public void modifier(Abonne abonne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(abonne);
		session.getTransaction().commit();
	}

	@Override
	public void supprimer(Abonne abonne) {
		
	}
	
	@Override
	public Abonne findById(Long id) {
		Session session=HibernateUtil.getSession();
		return (Abonne) session.get(Abonne.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abonne> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Abonne a").list();
	}

}
