package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.RoleDAO;
import com.neo.domaine.Role;
import com.neo.utility.HibernateUtil;

public class RoleDAOImpl implements RoleDAO{

	@Override
	public Role parId(int id) {
		Session session=HibernateUtil.getSession();
		return (Role) session.get(Role.class, id);
	}

	@Override
	public void creer(Role role) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(role);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Role role) {
		Session session=HibernateUtil.getSession();
		session.update(role);
		session.flush();
	}

	@Override
	public void supprimer(Role role) {
		Session session=HibernateUtil.getSession();
		session.delete(role);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("FROM Role").list();
	}

}
