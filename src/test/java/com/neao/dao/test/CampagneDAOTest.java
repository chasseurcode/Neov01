package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Campagne;

public class CampagneDAOTest {

	Campagne c1;
	CampagneDAO dao;

	@Before
	public void setUp() throws Exception {
		dao=new CampagneDaoimpl();
		c1=new Campagne();
		c1.setIntitule("campagne1");
		c1.setDateDebut(new Date());
		c1.setDateFin(new Date());
	}

	@Test
	public void test() {
		//creation
		dao.creer(c1);
		Campagne c2=dao.findById(c1.getId());
		assertNotNull(c2);
		assertEquals(c2.getIntitule(), c1.getIntitule());
		
		//modification
		c1.setIntitule("la camp");
		dao.modifier(c1);
		Campagne c3=dao.findById(c1.getId());
		assertNotNull(c3);
		assertEquals(c3.getIntitule(), "la camp");
		
		//lister
		List<Campagne> listes=dao.lister();
		assertNotNull(listes);
		assertEquals(listes.size(), 1);




	}

}
