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

import com.neo.dao.AbonneeDAO;
import com.neo.dao.CampagneDAO;
import com.neo.dao.ClientDAO;
import com.neo.dao.MessageDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.AbonneDAOImpl;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.daoImpl.MessageDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Campagne;
import com.neo.domaine.Message;
import com.neo.domaine.Publicite;
import com.neo.domaine.Utilisateur;

@ManagedBean
@SessionScoped
public class AppBean {
	private int nbreMessage;
	private List<Message> msgList;
	Subject currentUser;
	private MessageDAO msgDAO;
	private CampagneDAO campDao;
	private AbonneeDAO abonneeDAO;
	private ClientDAO clientDAO;
	private UtilisateurDAO userDAO;
	private int nbreAbonne=0;
	private int nbreCamp=0;
	private int nbrePubs=0;
	private int nbreClients=0;
	
	@PostConstruct
	public void loadMessages() {
		currentUser=SecurityUtils.getSubject();
		msgDAO=new MessageDAOImpl();
		userDAO=new UtilisateurDAOImpl();
		clientDAO=new ClientDaoImpl();
		campDao=new CampagneDaoimpl();
		abonneeDAO=new AbonneDAOImpl();
		Utilisateur utilisateur=userDAO.findByCompte(currentUser.getPrincipal().toString());
		msgList=msgDAO.lister(utilisateur);
		nbreMessage=msgList.size();
		setNbreCamp(campDao.listerCampEncours().size());
		setNbreAbonne(abonneeDAO.lister().size());
		setNbreClients(clientDAO.lister().size());
		nbrePubs();		
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
	
	public long nombreTotalDeVue(){
		long somme=0;
		for(Campagne c: campDao.listerCampEncours()){
			for(Publicite p: c.getPublicites()){
				somme=somme+p.getNbreVue();
			}
		}
		return somme;
	}
	
	public long nombreTotalDeVueReel(){
		long somme=0;
		for(Campagne c: campDao.listerCampEncours()){
			somme=somme+c.nombreDeVues();
		}
		return somme;
	}
	
 	private void nbrePubs(){
		int val=0;
		for(Campagne c:campDao.listerCampEncours()){
			val=val+c.getPublicites().size();
		}
		setNbrePubs(val);
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

	public int getNbreAbonne() {
		return nbreAbonne;
	}

	public void setNbreAbonne(int nbreAbonne) {
		this.nbreAbonne = nbreAbonne;
	}

	public int getNbreCamp() {
		return nbreCamp;
	}

	public void setNbreCamp(int nbreCamp) {
		this.nbreCamp = nbreCamp;
	}

	public int getNbrePubs() {
		return nbrePubs;
	}

	public void setNbrePubs(int nbrePubs) {
		this.nbrePubs = nbrePubs;
	}

	public int getNbreClients() {
		return nbreClients;
	}

	public void setNbreClients(int nbreClients) {
		this.nbreClients = nbreClients;
	}
	
	
	
}
