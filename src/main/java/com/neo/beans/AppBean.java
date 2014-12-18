package com.neo.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

@ManagedBean
@SessionScoped
public class AppBean {
	private int nbreMessage;
	private List<Message> msgList;
	Subject currentUser;
	
	private UtilisateurDAO userDAO;
	
	@PostConstruct
	public void loadMessages() {
		currentUser=SecurityUtils.getSubject();
		userDAO=new UtilisateurDAOImpl();
		Utilisateur utilisateur=userDAO.findByCompte(currentUser.getPrincipal().toString());
		msgList=utilisateur.getMessages();
		nbreMessage=msgList.size();
	}
	public AppBean() {
	
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
