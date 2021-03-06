package com.neao.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.MessageDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.MessageDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;

public class MessageDAOTest {
	private Message m1,m2,m3;

	private UtilisateurDAO daoUser;
	private MessageDAO msgDao;
	@Before
	public void setUp() throws Exception {
		daoUser=new UtilisateurDAOImpl();
		msgDao=new MessageDAOImpl();
		m1=new Message();
		m1.setObjet("Raison de vivre");
		m1.setCorps("Bonjour la mec");
		m1.setUtilisateur(daoUser.findByCompte("Mohamed"));
		
		m2=new Message();
		m2.setUtilisateur(daoUser.findById(new Long(1)));
		m2.setCorps("Allo la terre");
		m2.setObjet("Bientot rupture de carte");
		m2.setUtilisateur(daoUser.findByCompte("Mohamed"));
		
		m3=new Message();
		m3.setUtilisateur(daoUser.findByCompte("Mohamed"));
		m3.setCorps("Allo la terre");
		m3.setObjet("Système load");
		m3.setUtilisateur(daoUser.findByCompte("Mohamed"));
	}

	@Test
	public void allTest() {
		//creation
		msgDao.creer(m1);
		msgDao.creer(m2);
		msgDao.creer(m3);
		Message msg=msgDao.findMessageById(m1.getId());
		assertNotNull(msg);
		
		
		List<Message> msgList=msgDao.lister(daoUser.findByCompte("Mohamed"));
		assertNotNull(msgList);
		assertEquals(msgList.size(), 3);
		for(Message m: msgList){
			System.out.println(m.getCorps());
		}
	}

}
