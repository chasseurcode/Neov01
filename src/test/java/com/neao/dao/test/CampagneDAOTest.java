package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Publicite;
import com.neo.domaine.Reglement;
import com.neo.domaine.Tarif;
import com.neo.domaine.TarifNotification;

public class CampagneDAOTest {

	Campagne c1;
	CampagneDAO dao;

	@Before
	public void setUp() throws Exception {
		dao=new CampagneDaoimpl();
		c1=new Campagne();
		c1.setIntitule("campagne1");
		c1.setDateDebut(new Date());
		c1.setDateFin(new Date());
		
		Client client=new Client();
		client.setNom("ALSOME");
		client.setAdresse("10,Res Assabah imm Berkane Apt7");
		client.setRaisonSociale("Vendeur de chaussure");
		
		c1.setClient(client);
		
		Publicite p1=new Banniere();
		p1.setIntitule("Promo 401");
		p1.setNbreVue(30000);
		Tarif t1=new TarifNotification();
		t1.setTarifclient((float) 0.08);
		t1.setEnVigueur(true);
		p1.setTarif(t1);
		
		Publicite p2=new Banniere();
		p2.setIntitule("Chaussure Fresh");
		p2.setNbreVue(30000);
		Tarif t2=new TarifNotification();
		t2.setTarifclient((float) 0.08);
		t2.setEnVigueur(true);
		p2.setTarif(t2);
		
		c1.addPublicite(p1);
		c1.addPublicite(p2);
		
		Reglement reg1=new Reglement();
		reg1.setMontant(200);
		
		c1.addReglement(reg1);
		
	}

	@Test
	public void test() {
		//creation
		dao.creer(c1);
		Campagne c2=dao.findById(c1.getId());
		assertNotNull(c2);
		assertEquals(c2.getIntitule(), c1.getIntitule());
		
		//modification
		c1.setIntitule("la camp");
		dao.modifier(c1);
		Campagne c3=dao.findById(c1.getId());
		assertNotNull(c3);
		assertEquals(c3.getIntitule(), "la camp");
		
		//lister
		List<Campagne> listes=dao.lister();
		assertNotNull(listes);
		assertEquals(listes.size(), 1);
	}

}
