package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.ReglementDAO;
import com.neo.daoImpl.ReglementDaoImpl;
import com.neo.domaine.Reglement;

public class ReglementDAOTest {

	Reglement reg;
	ReglementDAO dao;

	@Before
	public void setUp() throws Exception {
		dao=new ReglementDaoImpl();
		reg=new Reglement();
		reg.setType("cash");
		reg.setMontant(100);
	}

	@Test
	public void test() {
		//creation
		dao.creer(reg);
		Reglement r=dao.findById(reg.getId());
		assertNotNull(r);
		assertEquals(r.getType(), reg.getType());
		
		//modification
		reg.setType("cheque");
		dao.modifier(reg);
		Reglement r1=dao.findById(reg.getId());
		assertNotNull(r1);
		assertEquals(r1.getType(), "cheque");
		
		//liste
		List<Reglement> liste=dao.lister();
		assertNotNull(liste);
		assertEquals(liste.size(), 1);
	}

}
