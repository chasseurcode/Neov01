package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.UtilisateurDAO;
import com.neo.domaine.Utilisateur;
import com.neo.utility.HibernateUtil;

public class UtilisateurDAOImpl implements UtilisateurDAO{

	public void creer(Utilisateur utilisateur) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(utilisateur);
		session.getTransaction().commit();
		
	}

	public void modifier(Utilisateur utilisateur) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.merge(utilisateur);
		session.getTransaction().commit();
	}

	public void supprimer(Utilisateur utilisateur) {
		
	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> lister() {
		Session session=HibernateUtil.getSession();
		return	(List<Utilisateur>) session.createQuery("From Utilisateur where supprimer = :etat")
				.setBoolean("etat", false).list();
	}

	public Utilisateur findById(int id) {
		Session session=HibernateUtil.getSession();
		return (Utilisateur) session.get(Utilisateur.class, id);
	}

	public Utilisateur findByCompte(String compte) {
		Session session=HibernateUtil.getSession();
		return (Utilisateur) session.createQuery("FROM Utilisateur where compte= :nom")
				.setString("nom", compte).uniqueResult();
	}
	
}
