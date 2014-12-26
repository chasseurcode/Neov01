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
		appl.setTarifabonne((float) 0.5);
		appl.setTarifabonne((float) 0.3);
		notif=new TarifNotification();
		notif.setTarifabonne((float) 0.4);
		notif.setTarifclient((float) 0.2);
		texte=new TarifTextuelle();
		texte.setTarifabonne((float) 0.6);
		texte.setTarifclient((float) 0.3);
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
