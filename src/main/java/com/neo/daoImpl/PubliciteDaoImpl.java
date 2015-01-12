package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.PubliciteDAO;
import com.neo.domaine.Banniere;
import com.neo.domaine.Domaine;
import com.neo.domaine.Publicite;
import com.neo.domaine.Textuelle;
import com.neo.utility.HibernateUtil;

public class PubliciteDaoImpl implements PubliciteDAO{
	@Override
	public void creer(Banniere banniere) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(banniere);
		session.getTransaction().commit();
	}

	@Override
	public void creer(Textuelle textuelle) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(textuelle);
		session.getTransaction().commit();		
	}

	@Override
	public void modifier(Banniere banniere) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(banniere);
		session.getTransaction().commit();		
	}

	@Override
	public void modifier(Textuelle textuelle) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(textuelle);
		session.getTransaction().commit();		
	}

	@Override
	public void supprimer(Banniere banniere) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(Textuelle textuelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Banniere findBanniereById(long id) {
		Session session=HibernateUtil.getSession();
		return (Banniere) session.get(Banniere.class, id);
	}

	@Override
	public Textuelle findTextuelleById(long id) {
		Session session=HibernateUtil.getSession();
		return (Textuelle) session.get(Textuelle.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banniere> listerBanniere() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Banniere").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Textuelle> listerTextuelle() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Textuelle").list();
	}

	@Override
	public Domaine findDomaineById(long id){
		Session session=HibernateUtil.getSession();
		return (Domaine) session.get(Domaine.class, id);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Domaine> listerDomaine(){
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Domaine").list();
	}

	@Override
	public void creer(Domaine domaine) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(domaine);
		session.getTransaction().commit();	
	}

	@Override
	public void modifier(Domaine domaine) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(domaine);
		session.getTransaction().commit();
	}

	@Override
	public void supprmer(Domaine domaine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Publicite findPubliciteById(long id) {
		Session session=HibernateUtil.getSession();
		
		return (Publicite) session.get(Publicite.class, id);
	}

}
