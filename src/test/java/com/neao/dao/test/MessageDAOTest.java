package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.MessageDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.MessageDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;

public class MessageDAOTest {
	private Message m1,m2;

	private UtilisateurDAO daoUser;
	private MessageDAO msgDao;
	@Before
	public void setUp() throws Exception {
		daoUser=new UtilisateurDAOImpl();
		msgDao=new MessageDAOImpl();
		m1=new Message();
		m1.setCorps("Bonjour la mec");
		m1.setDateMaj(new Date());
		m1.setUtilisateur(daoUser.findById(1));
		
		m2=new Message();
		m2.setUtilisateur(daoUser.findById(1));
		m2.setCorps("Allo la terre");
		m2.setDateMaj(new Date());
		m2.setUtilisateur(daoUser.findById(1));
	}

	@Test
	public void allTest() {
		//creation
		msgDao.creer(m1);
		msgDao.creer(m2);
		Message msg=msgDao.findMessageById(m1.getId());
		assertNotNull(msg);
		
		
		List<Message> msgList=msgDao.lister(daoUser.findById(1));
		assertNotNull(msgList);
		assertEquals(msgList.size(), 2);
		for(Message m: msgList){
			System.out.println(m.getCorps());
		}
	}

}
