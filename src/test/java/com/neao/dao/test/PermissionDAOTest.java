package com.neao.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.neo.dao.PermissionDAO;
import com.neo.daoImpl.PermissionDAOImpl;
import com.neo.domaine.Permission;

public class PermissionDAOTest {
	Permission p1,p2;
	List<String> ressource;
	PermissionDAO permissionDAO;
	@Before
	public void setUp() throws Exception {
		permissionDAO=new PermissionDAOImpl();
		p1=new Permission("Facturation:creer");
		p2=new Permission("Facturation:modifier");
		ressource=new ArrayList<String>();
		ressource.add("Campagne");
		ressource.add("Client");
		ressource.add("Publicite");
		ressource.add("Depense");
		ressource.add("Paiement");
		ressource.add("Reglement");
		ressource.add("Compte");
	}

	@Test
	public void AllTest() {
		//creation
		permissionDAO.creer(p1);
		Permission p=permissionDAO.findById(new Long(1));
		assertNotNull(p);
		assertEquals(p.getNom(), p1.getNom());
		
		//Modification
		p.setNom("Diagnostic:creer");
		permissionDAO.modifier(p);
		Permission mPerm=permissionDAO.findById(new Long(1));
		assertNotNull(mPerm);
		assertEquals(mPerm.getNom(), "Diagnostic:creer");
		
		//Lister
		List<Permission> permissions=permissionDAO.lister();
		assertNotNull(permissions);
		assertEquals(permissions.size(), 1);
		
		//Suppression
		permissionDAO.supprimer(p);
		List<Permission> list=permissionDAO.lister();
		assertNotNull(list);
		assertEquals(list.size(), 0);
		
		for(String res: ressource){
			Permission pv=new Permission(res+":voir");
			Permission pc=new Permission(res+":creer");
			Permission pu=new Permission(res+":modifier");
			Permission pr=new Permission(res+":supprimer");
			permissionDAO.creer(pv);
			permissionDAO.creer(pc);
			permissionDAO.creer(pu);
			permissionDAO.creer(pr);
		}
	}

}
