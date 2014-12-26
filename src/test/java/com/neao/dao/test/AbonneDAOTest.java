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
		
		Abonne ab1=new Abonne();
		ab1.setCompte("Alex");
		ab1.setPrenom("Ali");
		ab1.setEmail("aser@gmail.com");

		Abonne ab2=new Abonne();
		ab2.setCompte("Solaire");
		ab2.setPrenom("Dosseh");
		ab2.setEmail("dosel@gmail.com");

		Abonne ab3=new Abonne();
		ab3.setCompte("Colombo");
		ab3.setPrenom("Alfred");
		ab3.setEmail("bolid@gmail.com");

		Abonne ab4=new Abonne();
		ab4.setCompte("konor");
		ab4.setPrenom("Floyd");
		ab4.setEmail("konoto@gmail.com");
		
		Abonne ab5=new Abonne();
		ab5.setCompte("Antoine");
		ab5.setPrenom("Nolane");
		ab5.setEmail("solr@gmail.com");

		Abonne ab6=new Abonne();
		ab6.setCompte("Arisote");
		ab6.setPrenom("Fred");
		ab6.setEmail("fred@gmail.com");
		
		dao.creer(ab1);
		dao.creer(ab2);
		dao.creer(ab3);
		dao.creer(ab4);
		dao.creer(ab6);

	}

}
