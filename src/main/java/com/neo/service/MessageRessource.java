package com.neo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Utilisateur;

@Path("/messages")
public class MessageRessource {
	UtilisateurDAO dao=new UtilisateurDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessage() {
		Subject subject=SecurityUtils.getSubject();
		String compte=(String) subject.getPrincipal();
		Utilisateur user=dao.findByCompte("Mohamed");
		
		return Response.ok("").build();
	}
}
