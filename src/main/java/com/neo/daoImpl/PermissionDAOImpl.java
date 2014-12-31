package com.neo.daoImpl;

import java.util.List;

import org.hibernate.Session;

import com.neo.dao.PermissionDAO;
import com.neo.domaine.Permission;
import com.neo.utility.HibernateUtil;

public class PermissionDAOImpl implements PermissionDAO {

	@Override
	public Permission findById(Long id) {
		Session session=HibernateUtil.getSession();
		return (Permission) session.get(Permission.class, id);
	}

	@Override
	public void creer(Permission permission) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(permission);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Permission permission) {
		Session session=HibernateUtil.getSession();
		session.update(permission);
		session.flush();
	}

	@Override
	public void supprimer(Permission permission) {
		Session session=HibernateUtil.getSession();
		session.delete(permission);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("FROM Permission").list();
	}

}
