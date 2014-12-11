package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.PaiementDAO;
import com.neo.daoImpl.PaiementDaoImpl;
import com.neo.domaine.Paiement;

public class PaiementDAOTest {

	Paiement p;
	PaiementDAO dao;

	@Before
	public void setUp() throws Exception {
		p=new Paiement();
		p.setMontant(34);
		p.setDate(new Date());
		dao=new PaiementDaoImpl();
	}

	@Test
	public void test() {
		//creation
		dao.creer(p);
		Paiement p1=dao.findById(p.getId());
		assertNotNull(p1);
		assertEquals(p.getId(), p1.getId());
		
		//modification
		p.setMontant(40);
		dao.modifier(p);
		Paiement p2=dao.findById(p.getId());
		assertNotNull(p2);
		assertEquals(p2.getId(), 1);
		
		//lister
		List<Paiement> liste=dao.lister();
		assertNotNull(liste);
		assertEquals(liste.size(), 1);

	}

}
