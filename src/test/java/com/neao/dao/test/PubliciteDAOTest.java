package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.PubliciteDAO;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Textuelle;

public class PubliciteDAOTest {

	PubliciteDAO dao;
	Banniere banniere;
	Textuelle texte;

	@Before
	public void setUp() throws Exception {
		dao=new PubliciteDaoImpl();
		banniere=new Banniere();
		banniere.setIntitule("maa banniere");
		banniere.setNbreAppel(2);
		texte=new Textuelle();
		texte.setIntitule("monn texte");
		texte.setContenu("hello world ! ");
		
	}

	@Test
	public void test() {
		//creation
				dao.creer(banniere);
				dao.creer(texte);
				Banniere b=dao.findBanniereById(banniere.getId());
				Textuelle t=dao.findTextuelleById(texte.getId());
				assertNotNull(b);
				assertNotNull(t);
				assertEquals(b.getIntitule(), banniere.getIntitule());
				assertEquals(t.getIntitule(), texte.getIntitule());
				
				//modification
				banniere.setIntitule("ma banniere");
				texte.setIntitule("mon texte");
		        dao.modifier(banniere);
		        dao.modifier(texte);
		        Banniere ba=dao.findBanniereById(banniere.getId());
				Textuelle tex=dao.findTextuelleById(texte.getId());		
				assertNotNull(ba);
				assertNotNull(tex);
				assertEquals(ba.getIntitule(), "ma banniere");
				assertEquals(tex.getIntitule(), "mon texte");
				
				//lister
				List<Banniere> listeBa=dao.listerBanniere();
				assertNotNull(listeBa);
				assertEquals(listeBa.size(), 1);
				List<Textuelle> listeTe=dao.listerTextuelle();
				assertNotNull(listeTe);
				assertEquals(listeTe.size(), 1);
	}

}
