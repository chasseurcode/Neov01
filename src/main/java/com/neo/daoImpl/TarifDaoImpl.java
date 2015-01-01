package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.TarifDAO;
import com.neo.domaine.Tarif;
import com.neo.domaine.TarifAppel;
import com.neo.domaine.TarifNotification;
import com.neo.domaine.TarifTextuelle;
import com.neo.utility.HibernateUtil;

public class TarifDaoImpl implements TarifDAO{

	@Override
	public void creer(TarifNotification notif) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(notif);
		session.getTransaction().commit();
	}

	@Override
	public void creer(TarifAppel appel) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(appel);
		session.getTransaction().commit();
	}

	@Override
	public void creer(TarifTextuelle texte) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(texte);
		session.getTransaction().commit();		
	}

	@Override
	public void modifier(TarifAppel appel) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(appel);
		session.getTransaction().commit();		
	}

	@Override
	public void modifier(TarifNotification notif) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(notif);
		session.getTransaction().commit();		
	}

	@Override
	public void modifier(TarifTextuelle texte) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(texte);
		session.getTransaction().commit();		
	}

	@Override
	public void supprimer(TarifAppel appel) {
		
	}

	@Override
	public void supprimer(TarifNotification tarif) {
		
	}

	@Override
	public void supprimer(TarifTextuelle tarif) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TarifAppel findTarifAppelById(long id) {
		Session session=HibernateUtil.getSession();
		return (TarifAppel) session.get(TarifAppel.class, id);
	}

	@Override
	public TarifNotification findNotificationById(long id) {
		Session session=HibernateUtil.getSession();
		return (TarifNotification) session.get(TarifNotification.class, id);
	
	}

	@Override
	public TarifTextuelle findTarifTextuelleById(long id) {
		Session session=HibernateUtil.getSession();
		return (TarifTextuelle) session.get(TarifTextuelle.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TarifAppel> listerTarifappel() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from TarifAppel").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TarifNotification> listerTarifNotif() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from TarifNotification").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TarifTextuelle> listerTarifText() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from TarifTextuelle").list();
	}

	@Override
	public void modifier(Tarif tarif) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(tarif);
		session.getTransaction().commit();
	}

	@Override
	public void updateAllApp() {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.createQuery("update TarifAppel ta SET ta.enVigueur = :etat ").setBoolean("etat", false)
		.executeUpdate();
		
		session.getTransaction().commit();
	}

	@Override
	public void updateAllText() {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.createQuery("UPDATE TarifTextuelle  tt SET tt.enVigueur = :etat ").setBoolean("etat", false)
		.executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public void updateAllNotif() {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.createQuery("UPDATE TarifNotification tn SET tn.enVigueur = :etat ").setBoolean("etat", false)
		.executeUpdate();
		session.getTransaction().commit();
	
	}

	@Override
	public TarifTextuelle tarifTextuelleEnvigueur() {
		Session session=HibernateUtil.getSession();
		return (TarifTextuelle) session.createQuery("from TarifTextuelle where enVigueur = :etat").setBoolean("etat", true).uniqueResult();
	}

	@Override
	public TarifAppel tarifAppelEnvigueur() {
		Session session=HibernateUtil.getSession();
		return (TarifAppel) session.createQuery("from TarifAppel where enVigueur = :etat").setBoolean("etat", true).uniqueResult();
	}

	@Override
	public TarifNotification tarifNotificationEnvigueur() {
		Session session=HibernateUtil.getSession();
		return (TarifNotification) session.createQuery("from TarifNotification where enVigueur = :etat").setBoolean("etat", true).uniqueResult();
	}


}
