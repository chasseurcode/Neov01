package com.neo.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.MessageDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.MessageDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

@ManagedBean
@SessionScoped
public class AppBean {
	private int nbreMessage;
	private List<Message> msgList;
	Subject currentUser;
	private MessageDAO msgDAO;
	
	private UtilisateurDAO userDAO;
	
	@PostConstruct
	public void loadMessages() {
		currentUser=SecurityUtils.getSubject();
		msgDAO=new MessageDAOImpl();
		userDAO=new UtilisateurDAOImpl();
		Utilisateur utilisateur=userDAO.findByCompte(currentUser.getPrincipal().toString());
		msgList=msgDAO.lister(utilisateur);
		nbreMessage=msgList.size();
	}
	
	public void errorPage() {
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance()
		         .getExternalContext().getResponse();
		try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getNbreMessage() {
		return nbreMessage;
	}

	public void setNbreMessage(int nbreMessage) {
		this.nbreMessage = nbreMessage;
	}

	public List<Message> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<Message> msgList) {
		this.msgList = msgList;
	}
	
}
