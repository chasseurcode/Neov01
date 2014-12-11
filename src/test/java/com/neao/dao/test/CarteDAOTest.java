package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.CarteDAO;
import com.neo.daoImpl.CarteDaoImpl;
import com.neo.domaine.Carte;

public class CarteDAOTest {

	Carte c;
	CarteDAO dao;
	
	@Before
	public void setUp() throws Exception {
		c=new Carte();
		c.setOperateur("maroc telecom");
		c.setMontant(20);
		c.setNumero("1234567890");
		dao=new CarteDaoImpl();
	}

	@Test
	public void test() {
		//creation et lister
		dao.creer(c);
		Carte c1=dao.findById(c.getId());
		assertNotNull(c1);
		assertEquals(c.getOperateur(), c1.getOperateur());

		//modification
		c.setOperateur("meditel");
		dao.modifier(c);
		Carte c2=dao.findById(c.getId());
		assertNotNull(c2);
		assertEquals(c2.getOperateur(), c2.getOperateur());
		
		//lister
		List<Carte> carte=dao.lister();
		assertNotNull(carte);
		assertEquals(carte.size(), 1);

	}

}
