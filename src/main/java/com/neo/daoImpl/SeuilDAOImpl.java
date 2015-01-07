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
	
	@Override
	public void updateAll(){
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.createQuery("UPDATE Seuil tn SET tn.enVigueur = :etat ").setBoolean("etat", false)
		.executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public Seuil findSeuilEnVigueur() {
		Session session=HibernateUtil.getSession();
		return (Seuil) session.createQuery("from Seuil where enVigueur = :etat")
				.setBoolean("etat", true)
				.uniqueResult();
	}

}
