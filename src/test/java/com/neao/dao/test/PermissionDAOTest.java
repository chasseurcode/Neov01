package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.PermissionDAO;
import com.neo.daoImpl.PermissionDAOImpl;
import com.neo.domaine.Permission;

public class PermissionDAOTest {
	Permission p1,p2;
	PermissionDAO permissionDAO;
	@Before
	public void setUp() throws Exception {
		permissionDAO=new PermissionDAOImpl();
		p1=new Permission("Facturation:creer");
		p2=new Permission("Facturation:modifier");
	}

	@Test
	public void AllTest() {
		//creation
		permissionDAO.creer(p1);
		Permission p=permissionDAO.findById(2);
		assertNotNull(p);
		assertEquals(p.getNom(), p1.getNom());
		
		//Modification
		p.setNom("Diagnostic:creer");
		permissionDAO.modifier(p);
		Permission mPerm=permissionDAO.findById(2);
		assertNotNull(mPerm);
		assertEquals(mPerm.getNom(), "Diagnostic:creer");
		
		//Lister
		List<Permission> permissions=permissionDAO.lister();
		assertNotNull(permissions);
		assertEquals(permissions.size(), 2);
		
		//Suppression
		permissionDAO.supprimer(p);
		List<Permission> list=permissionDAO.lister();
		assertNotNull(list);
		assertEquals(list.size(), 1);
	}

}
