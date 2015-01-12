package com.neo.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.neo.dao.CampagneDAO;
import com.neo.domaine.Campagne;
import com.neo.domaine.Facture;
import com.neo.utility.HibernateUtil;

public class CampagneDaoimpl implements CampagneDAO{

	@Override
	public void creer(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(campagne);
		session.getTransaction().commit();
	}

	@Override
	public void modifier(Campagne campagne) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.update(campagne);
		session.getTransaction().commit();
	}

	@Override
	public void supprimer(Campagne campagne) {

	}

	@Override
	public Campagne findById(long id) {
		Session session=HibernateUtil.getSession();
		return (Campagne) session.get(Campagne.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Campagne> lister() {
		Session session=HibernateUtil.getSession();
		return session.createQuery("from Campagne c").list();
	}


	@Override
	public void creer(Facture facture) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(facture);
		session.getTransaction().commit();
	}

	@Override
	public Facture findLastRecord() {
		Session session=HibernateUtil.getSession();
		return (Facture) session.createQuery("from Facture ORDER BY date DESC")
				.setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<Campagne> listerCampAttente() {
		List<Campagne> camps=new ArrayList<Campagne>();
		for(Campagne ca:lister()){
			if(ca.getReglements().size()==0){
				camps.add(ca);
			}
		}
		return camps;
	}

	@Override
	public List<Campagne> listerCampEncours() {
		List<Campagne> camps=new ArrayList<Campagne>();
		for(Campagne ca:lister()){
			if(((ca.getDateFin().compareTo(new Date()) >0) || (ca.getDateFin().compareTo(new Date()) ==0))
					&& (ca.getReglements().size()>0)){
				camps.add(ca);
			}
		}
		return camps;
	}

	@Override
	public List<Campagne> listerCampTerminees() {
		List<Campagne> camps=new ArrayList<Campagne>();
		for(Campagne ca:lister()){
			if((ca.getDateFin().compareTo(new Date())<0) && (ca.getReglements().size()>0) ){
				camps.add(ca);
			}
		}	
		return camps;
	}

	@Override
	public Campagne findLastCamp() {
		Session session=HibernateUtil.getSession();
		return (Campagne) session.createQuery("from Campagne ORDER BY created DESC")
				.setMaxResults(1)
				.uniqueResult();

	}

	@Override
	public List<Campagne> lister(Long id) {
		List<Campagne> camps=new ArrayList<Campagne>();
		for(Campagne ca: lister()){
			if(ca.getClient().getId()!=null && ca.getClient().getId()==id)
				camps.add(ca);
		}
		return camps;
	}

	@Override
	public List<Campagne> listerEncours(Long id) {
		List<Campagne> camps=new ArrayList<Campagne>();
		for(Campagne ca:lister()){
			if(((ca.getDateFin().compareTo(new Date()) >0) || (ca.getDateFin().compareTo(new Date()) ==0))
					&& (ca.getReglements().size()>0) && ca.getClient().getId()==id){
				camps.add(ca);
			}
		}
		return camps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> listerFacture(Long id) {
		Session session=HibernateUtil.getSession();
		List<Facture> facts=new ArrayList<Facture>();
		List<Facture> factures=new ArrayList<Facture>();
		facts.addAll(session.createQuery("From Facture").list());
		for(Facture f: facts){
			if(f.getCampagne().getId()==id){
				factures.add(f);
			}
		}
		return factures;
	}




}
