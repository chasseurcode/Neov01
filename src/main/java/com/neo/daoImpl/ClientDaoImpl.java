package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.ClientDAO;
import com.neo.domaine.Client;
import com.neo.utility.HibernateUtil;

public class ClientDaoImpl implements ClientDAO{

	@Override
	public void creer(Client client) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(client);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Client client) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(client);
		session.getTransaction().commit();
	}

	@Override
	public void supprimer(Client client) {
		
	}
	
	@Override
	public Client findById(Long id) {
		Session session=HibernateUtil.getSession();
		return (Client) session.get(Client.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Client").list();
	}

	@Override
	public Client findLastRecord() {
		Session session=HibernateUtil.getSession();
		return (Client) session.createQuery("from Client ORDER BY id DESC")
                .setMaxResults(1)
                .uniqueResult();
	}

	@Override
	public Client findByCompte(String compte) {
		Session session=HibernateUtil.getSession();
		return (Client) session.createQuery("FROM Client where compte= :nom")
				.setString("nom", compte).uniqueResult();
	}



	

}
