package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.CarteDAO;
import com.neo.domaine.Carte;
import com.neo.utility.HibernateUtil;

public class CarteDaoImpl implements CarteDAO{

	@Override
	public void creer(Carte carte) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(carte);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Carte carte) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(carte);
		session.getTransaction().commit();
	}
	
	@Override
	public void supprimer(Carte carte) {
		
	}
	
	@Override
	public Carte findById(long id) {
		Session session=HibernateUtil.getSession();
		return (Carte) session.get(Carte.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Carte> lister() {
		Session session=HibernateUtil.getSession();
			return session.createQuery("from Carte").list();
	}

	

	

}
