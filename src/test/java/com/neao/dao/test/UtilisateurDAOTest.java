package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
		 RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		 Object salt = rng.nextBytes();

		user1=new Utilisateur();
		user1.setCompte("taram");
		String hashPass = new Sha256Hash("123456", salt,1024).toBase64();
		user1.setMotDePasse(hashPass);
		user1.setSaltMotDePasse(salt.toString());
		System.out.println(user1.getMotDePasse()+" : "+user1.getSaltMotDePasse());
		user2=new Utilisateur("bodie", "plus123", "abd.diallo@gmail.com", true);	
	}

	@Test
	public void allTest() {
		
		//creation
		dao.creer(user1);
		Utilisateur user=dao.findByCompte("taram");
		assertNotNull(user);
		assertEquals(user.getCompte(), user1.getCompte());
		
		//Modification
		user.setCompte("Mohamed");
		Role role=new Role("Ingenieur");
		role.addPermission(new Permission("Campagne:creer"));
		user.addRole(role);
		dao.modifier(user);
		Utilisateur modifUser=dao.findByCompte("Mohamed");
		assertNotNull(modifUser);
		
		//lister
		dao.creer(user2);
		List<Utilisateur> utilisateurs=dao.lister();
		assertNotNull(utilisateurs);
		assertEquals(utilisateurs.size(), 2);
	}
	
}
