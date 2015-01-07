package com.neo.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

@Path("/messages")
public class MessageRessource {
	UtilisateurDAO dao=new UtilisateurDAOImpl();
	private Logger logger=Logger.getLogger(getClass().getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage() {
		Subject subject=SecurityUtils.getSubject();
		String compte=(String) subject.getPrincipal();
		Utilisateur user=dao.findByCompte(compte);
		
		List<Message> mesages=user.getLastMessages();
		logger.info("Utilisateur: "+user.getCompte()+" - Nombre de messages: "+mesages.size());
		if (mesages.size()>0) {
			return mesages;
		}
		return null;
	}
}
