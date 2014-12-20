package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.RoleDAO;
import com.neo.daoImpl.RoleDAOImpl;
import com.neo.domaine.Permission;
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
		Role role=roleDAO.findById(2);
		assertNotNull(role);
		assertEquals(role.getNom(), role1.getNom());
		
		//Modifier
		role.setNom("Neurologue");
		roleDAO.modifier(role);
		Role mRole=roleDAO.findById(2);
		assertNotNull(mRole);
		assertEquals(mRole.getNom(), "Neurologue");
		
		//Lister
		roleDAO.creer(role2);
		List<Role> roList=roleDAO.lister();
		assertNotNull(roList);
		assertEquals(roList.size(), 3);
		
//		Role rol1=new Role("Client");
//		Role rol2=new Role("Caissier");
//		Role rol3=new Role("Commercial");
//		Role rol4=new Role("Admin");
//		
//		Permission p1=new Permission("Client:view");
//		Permission p2=new Permission("Client:edit");
//		Permission p3=new Permission("Client:update");
//		Permission p4=new Permission("Client:remove");
//		
//		rol2.addPermission(p1);
//		rol2.addPermission(p2);
//		rol2.addPermission(p3);
//		rol2.addPermission(p4);
//		
//		roleDAO.creer(rol1);
//		roleDAO.creer(rol2);
//		roleDAO.creer(rol3);
//		roleDAO.creer(rol4);
	}

}
