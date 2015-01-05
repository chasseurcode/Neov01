package com.neo.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

@Path("/messages")
public class MessageRessource {
	UtilisateurDAO dao=new UtilisateurDAOImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage() {
		//Subject subject=SecurityUtils.getSubject();
		//String compte=(String) subject.getPrincipal();
		Utilisateur user=dao.findByCompte("Mohamed");
		List<Message> mesages=user.getLastMessages();
		if (mesages.size()>0) {
			return mesages;
		}
		return null;
	}
}
