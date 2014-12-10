package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.RoleDAO;
import com.neo.daoImpl.RoleDAOImpl;
import com.neo.domaine.Role;

public class RoleDAOTest {
	private Role role1,role2;
	private RoleDAO roleDAO;
	@Before
	public void setUp() throws Exception {
		role1=new Role("Docteur");
		role2=new Role("infirmière");
		roleDAO=new RoleDAOImpl();
	}

	@Test
	public void allTest() {
		//Creation
		roleDAO.creer(role1);
		Role role=roleDAO.parId(2);
		assertNotNull(role);
		assertEquals(role.getNom(), role1.getNom());
		
		//Modifier
		role.setNom("Neurologue");
		roleDAO.modifier(role);
		Role mRole=roleDAO.parId(2);
		assertNotNull(mRole);
		assertEquals(mRole.getNom(), "Neurologue");
		
		//Lister
		roleDAO.creer(role2);
		List<Role> roList=roleDAO.lister();
		assertNotNull(roList);
		assertEquals(roList.size(), 3);
	}

}
