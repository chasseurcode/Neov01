package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.AbonneeDAO;
import com.neo.daoImpl.AbonneDAOImpl;
import com.neo.domaine.Abonne;

public class AbonneDAOTest {

	Abonne a1;
	AbonneeDAO dao;
	
	@Before
	public void setUp() throws Exception {
		a1=new Abonne("taram", new Date(), "1234", "1233");
		dao=new AbonneDAOImpl();
	}

	@Test
	public void test() {
		
		// creartion
		dao.creer(a1);
		Abonne a2= dao.findById(a1.getId());
		assertNotNull(a2);
		assertEquals(a1.getPrenom(), a2.getPrenom());
		
		//modification
		a1.setPrenom("Toto");;
		dao.modifier(a1);
		Abonne  a3=dao.findById(a1.getId());
		assertNotNull(a3);
		assertEquals(a3.getPrenom(), "Toto");
		
		//lister
		List<Abonne> liste=dao.lister();
		assertNotNull(liste);
		assertEquals(liste.size(), 1);
	}

}
