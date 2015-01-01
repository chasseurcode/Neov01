package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.ClientDAO;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.domaine.Client;

public class ClientDAOTest {

	Client c;
	ClientDAO dao;

	@Before
	public void setUp() throws Exception {
		c=new Client();
		c.setNom("Meditel");
		c.setEmail("meditel@meditel.ma");
		c.setRaisonSociale("meditel");
		c.setAdresse("rabat");
		dao=new ClientDaoImpl();
	}

	@Test
	public void test() {
		//ceation
		dao.creer(c);
		Client c1=dao.findById(c.getId());
		assertNotNull(c1);
		assertEquals(c1.getRaisonSociale(), c.getRaisonSociale());	
		
		//modification
		c.setRaisonSociale("orange");
		dao.modifier(c);
		Client c2=dao.findById(c.getId());
		assertNotNull(c2);
		assertEquals(c2.getRaisonSociale(), "orange");
		
		//lister
		List<Client> clients=dao.lister();
		assertNotNull(clients);
		assertEquals(clients.size(), 2);
		
		Client c3=new Client();
		c3.setNom("Bonita Soft");
		c3.setAdresse("La rue de la rosé");
		c3.setRaisonSociale("Editeur de logiciel");
		c3.setEmail("bonita@bonital.fr");
		c3.setTelehone("0656321275");
		
		Client c4=new Client();
		c4.setNom("Microsoft");
		c4.setAdresse("La 24ème rue Assault");
		c4.setRaisonSociale("Système d'exploitation");
		c4.setEmail("microsoft@live.fr");
		c4.setTelehone("0610321223");
		
		Client c5=new Client();
		c5.setNom("Loreal");
		c5.setAdresse("Paris 19 rue");
		c5.setRaisonSociale("Veudeur de produit cosmetique");
		c5.setEmail("loreal@paris.fr");
		c5.setTelehone("0656321231");
		
		Client C6=new Client();
		C6.setNom("France 3");
		C6.setAdresse("Avenue thom cruse");
		C6.setRaisonSociale("chaine de television");
		C6.setEmail("franceinfo@france3.fr");
		C6.setTelehone("0656321209");
		
		Client c7=new Client();
		c7.setNom("Novassure");
		c7.setAdresse("casablanca tour");
		c7.setRaisonSociale("Demarcheur d'assurance");
		c7.setTelehone("0578312288");
		c7.setEmail("novassure@novassure.ma");
		
		dao.creer(c3);
		dao.creer(c4);
		dao.creer(c5);
		dao.creer(C6);
		dao.creer(c7);
	}

}
