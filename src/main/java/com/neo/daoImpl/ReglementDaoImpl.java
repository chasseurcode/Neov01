package com.neo.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.neo.dao.ReglementDAO;
import com.neo.domaine.Reglement;
import com.neo.utility.HibernateUtil;

public class ReglementDaoImpl implements ReglementDAO{

	@Override
	public void creer(Reglement reglement) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(reglement);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Reglement reglement) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(reglement);
		session.getTransaction().commit();
	}

	@Override
	public Reglement findById(long id) {
		Session session=HibernateUtil.getSession();
		return (Reglement)session.get(Reglement.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reglement> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Reglement").list();
	}

	@Override
	public List<Reglement> lister(Long id) {
		List<Reglement> regs=new ArrayList<Reglement>();
		for(Reglement reg: lister()){
			if(reg.getClient().getId()!=null && reg.getClient().getId()==id)
				regs.add(reg);
		}
		return regs;
	}

}
