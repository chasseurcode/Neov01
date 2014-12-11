package com.neao.dao.test;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.TarifDAO;
import com.neo.daoImpl.TarifDaoImpl;
import com.neo.domaine.TarifAppel;
import com.neo.domaine.TarifNotification;
import com.neo.domaine.TarifTextuelle;

public class TarifDAOTest {

	TarifAppel appl;
	TarifNotification notif;
	TarifTextuelle texte;
	TarifDAO dao;

	@Before
	public void setUp() throws Exception {
		appl=new TarifAppel();
		appl.setClient(11);
		appl.setAbonne(12);
		notif=new TarifNotification();
		notif.setAbonne(20);
		notif.setClient(30);
		texte=new TarifTextuelle();
		texte.setAbonne(50);
		texte.setClient(60);
		dao=new TarifDaoImpl();
	}

	@Test
	public void test() {
		//creation
		dao.creer(appl);
		dao.creer(notif);
		dao.creer(texte);
		
		
	}

}
