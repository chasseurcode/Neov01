package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Permission;
import com.neo.domaine.Role;
import com.neo.domaine.Utilisateur;

public class UtilisateurDAOTest {
	private Utilisateur user1,user2;
	private UtilisateurDAO dao;
	

	@Before
	public void setUp() throws Exception {
		dao=new UtilisateurDAOImpl();
		user1=new Utilisateur("taram", "ass2011", "tsayouba@gmail.com", true);
		user2=new Utilisateur("bodie", "plus123", "abd.diallo@gmail.com", true);
	}

	@Test
	public void allTest() {
		
		//creation
		dao.creer(user1);
		Utilisateur user=dao.parCompte("taram");
		assertNotNull(user);
		assertEquals(user.getCompte(), user1.getCompte());
		
		//Modification
		user.setCompte("Mohamed");
		user.setMotDePasse("anou123");
		Role role=new Role("Ingenieur");
		role.addPermission(new Permission("Campagne:creer"));
		user.addRole(role);
		dao.modifier(user);
		Utilisateur modifUser=dao.parCompte("Mohamed");
		assertNotNull(modifUser);
		assertEquals(modifUser.getMotDePasse(), "anou123");
		
		//lister
		dao.creer(user2);
		List<Utilisateur> utilisateurs=dao.lister();
		assertNotNull(utilisateurs);
		assertEquals(utilisateurs.size(), 2);
	}
	
}
