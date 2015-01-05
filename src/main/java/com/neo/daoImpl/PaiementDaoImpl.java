package com.neo.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.neo.dao.PaiementDAO;
import com.neo.domaine.Paiement;
import com.neo.utility.HibernateUtil;

public class PaiementDaoImpl implements PaiementDAO{

	@Override
	public void creer(Paiement paiement) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(paiement);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Paiement paiement) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(paiement);
		session.getTransaction().commit();
	}

	@Override
	public Paiement findById(long id) {
		Session session=HibernateUtil.getSession();
		return (Paiement) session.get(Paiement.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paiement> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Paiement").list();
	}

	@Override
	public List<Paiement> lister(Long id) {
		List<Paiement> paids=new ArrayList<Paiement>();
		for(Paiement pa: lister()){
			if(pa.getAbonne().getId()!=null && pa.getAbonne().getId()==id)
				paids.add(pa);
		}
		return paids;
	}

}
