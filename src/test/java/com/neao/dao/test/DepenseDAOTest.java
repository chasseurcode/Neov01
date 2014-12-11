package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.DepenseDAO;
import com.neo.daoImpl.DepenseDaoImpl;
import com.neo.domaine.Depense;

public class DepenseDAOTest {

	Depense d;
	DepenseDAO dao;
	
	@Before
	public void setUp() throws Exception {
		d=new Depense();
		d.setFournisseur("medtiel");
		d.setMontant(10);
		d.setIntitule("intitule");
		dao=new DepenseDaoImpl();
	}

	@Test
	public void test() {
		//creation
		dao.creer(d);
		Depense d1=dao.findById(d.getId());
		assertNotNull(d1);
		assertEquals(d.getIntitule(), d1.getIntitule());
		
		//modification
		d.setFournisseur("maroc");
		dao.modifier(d);
		Depense d2=dao.findById(d.getId());
		assertNotNull(d2);
		assertEquals(d2.getFournisseur(), "maroc");
		
		//lister
		List<Depense> liste=dao.lister();
		assertNotNull(liste);
		assertEquals(liste.size(), 1);
	}

}
