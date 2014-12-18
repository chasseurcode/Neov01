package com.neo.daoImpl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.neo.dao.MessageDAO;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;
import com.neo.utility.HibernateUtil;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public Message findMessageById(Long id) {
		Session session=HibernateUtil.getSession();
		return (Message) session.get(Message.class, id);
	}

	@Override
	public void creer(Message message) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
	}

	@Override
	public void supprimer(Message message) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.delete(message);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> lister(Utilisateur utilisateur) {
		Session session=HibernateUtil.getSession();
		return session.createCriteria(Message.class)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.add(Restrictions.idEq(new Long(utilisateur.getId())))
				.list();
	}

}
