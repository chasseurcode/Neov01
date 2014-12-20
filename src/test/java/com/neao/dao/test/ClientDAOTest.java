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
		
	}

}
