package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.MessageDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.MessageDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

@Path("/messages")
public class MessageRessource {
	UtilisateurDAO dao=new UtilisateurDAOImpl();
	Utilisateur abonne;
	MessageDAO msgDao=new MessageDAOImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage() {
		Subject subject=SecurityUtils.getSubject();
		String compte=(String) subject.getPrincipal();
		abonne=dao.findByCompte(compte);
		
		List<Message> mesages=msgDao.lister(abonne);
		System.out.println("Utilisateur: "+abonne.getCompte()+" - Nombre de messages: "+mesages.size());
		if (mesages.size()>0) {
			return mesages;
		}
		return null;
	}
	
	public List<Message> getLastMessages(List<Message> messages) {
		List<Message> msgs=new ArrayList<Message>();
		System.out.println(messages.size());
		for(Message m: messages){
			if(abonne.getUpdated().before(m.getCreated())){
				msgs.add(m);
			}
		}
		return msgs;
	}
}
