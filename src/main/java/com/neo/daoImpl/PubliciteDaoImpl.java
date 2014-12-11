package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.PubliciteDAO;
import com.neo.domaine.Publicite;
import com.neo.utility.HibernateUtil;

public class PubliciteDaoImpl implements PubliciteDAO{

	@Override
	public void creer(Publicite publicite) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(publicite);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void modifier(Publicite publicite) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(publicite);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void supprimer(Publicite publicite) {
		
	}
	
	@Override
	public Publicite findById(int id) {
		Session session=HibernateUtil.getSession();
		return (Publicite) session.get(Publicite.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicite> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Publicite").list();
	}

	

}
