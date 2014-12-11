package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.DepenseDAO;
import com.neo.domaine.Depense;
import com.neo.utility.HibernateUtil;

public class DepenseDaoImpl implements DepenseDAO{

	@Override
	public void creer(Depense depense) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(depense);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void modifier(Depense depense) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(depense);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Depense findById(int id) {
		Session session=HibernateUtil.getSession();
		return (Depense) session.get(Depense.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Depense> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Depense").list();
	}

}
