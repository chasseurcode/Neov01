package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.PubliciteDAO;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.domaine.Publicite;

public class PubliciteDAOTest {

	Publicite pub;
	PubliciteDAO dao;
	@Before
	public void setUp() throws Exception {
		dao=new PubliciteDaoImpl();
		pub=new Publicite();
		pub.setIntitule("lapub");
	}

	@Test
	public void test() {
		//creation
		dao.creer(pub);
		Publicite p=dao.findById(pub.getId());
		assertNotNull(p);
		assertEquals(p.getIntitule(), pub.getIntitule());
		
		//modification
		pub.setIntitule("lepubli");
		Publicite p2=dao.findById(pub.getId());
		assertNotNull(p2);
		assertEquals(p2.getIntitule(), "lepubli");
		
		//lister
		List<Publicite> liste=dao.lister();
		assertNotNull(liste);
		assertEquals(liste.size(), 1);
	}

}
