package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.CampagneDAO;
import com.neo.domaine.Campagne;
import com.neo.domaine.Facture;
import com.neo.utility.HibernateUtil;

public class CampagneDaoimpl implements CampagneDAO{

	@Override
	public void creer(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(campagne);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(campagne);
		session.getTransaction().commit();
	}

	@Override
	public void supprimer(Campagne campagne) {
		
	}
	
	@Override
	public Campagne findById(long id) {
		Session session=HibernateUtil.getSession();
		return (Campagne) session.get(Campagne.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Campagne> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Campagne c").list();
	}

	@Override
	public void creer(Facture facture) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(facture);
		session.getTransaction().commit();
	}

	@Override
	public Facture findLastRecord() {
		Session session=HibernateUtil.getSession();
		return (Facture) session.createQuery("from Facture ORDER BY date DESC")
                .setMaxResults(1)
                .uniqueResult();
	}

	

	

}
