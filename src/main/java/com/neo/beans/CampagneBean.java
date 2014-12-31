package com.neo.beans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import com.neo.dao.CampagneDAO;
import com.neo.dao.ClientDAO;
import com.neo.dao.PubliciteDAO;
import com.neo.dao.TarifDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.daoImpl.ClientDaoImpl;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.daoImpl.TarifDaoImpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Domaine;
import com.neo.domaine.Publicite;
import com.neo.domaine.Reglement;
import com.neo.domaine.Textuelle;
import com.neo.utility.Generateur;
import com.neo.utility.Generateur.Mode;
import com.neo.utility.TrouverChemin;

@ManagedBean
@SessionScoped
public class CampagneBean {

	private boolean showEditCamp=false;
	private boolean showPubMenu=false;
	private boolean parAppel,parNotification;
	private Part fichier;
	private CampagneDAO campagneDAO;
	private PubliciteDAO pubDAO;
	private TarifDAO tarifDAO;
	private ClientDAO clientDAO;
	private Campagne campagne;
	private String campListe;
	private Reglement reglement;
	private List<Publicite> pubTextuelle,pubBaniere;
	private List<Campagne> lesCampagnes,listeCamp;
	private List<String> domainesSelected=new ArrayList<String>();
	private List<Domaine> domaines;
	private Domaine domaine,current;
	private Textuelle textuelle;
	private Banniere banniere;



	public CampagneBean() {
		campagne=new Campagne();
		textuelle=new Textuelle();
		banniere=new Banniere();
		domaine=new Domaine();
		reglement=new Reglement();
		setCampagneDAO(new CampagneDaoimpl());
		setPubDAO(new PubliciteDaoImpl());
		setTarifDAO(new TarifDaoImpl());
		setDomaines(pubDAO.listerDomaine());

	}




	// affichage du formulaire dédition campagne
	public String afficherPub(Campagne campagne) {
		if(showEditCamp){
			setShowEditCamp(false);
		}
		else{
			setShowEditCamp(true);
		}
		setCampagne(campagne);
		return null;
	}


	//ajout de la publicite
	public void addPubliciteTextuelle(){
		System.out.println("ds addpub");
		for(String check: domainesSelected){
			Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
			textuelle.addDomaine(d);
			d=new Domaine();
		}
		textuelle.setTarif(tarifDAO.tarifTextuelleEnvigueur());
		campagne.addPublicite(textuelle);
		campagneDAO.modifier(campagne);
		textuelle=new Textuelle();
		domainesSelected.clear();

	}


	//upload de fichier
	public void uploadPubBanniere(){ 

		try {					
			String cheminImg=TrouverChemin.cheminImg();
			System.out.println(cheminImg);
			InputStream inputStream = fichier.getInputStream();          
			String nomFichier=Generateur.generateRandomString(18, Mode.ALPHANUMERIC).toUpperCase()+"."+getFileExtension(fichier);
			FileOutputStream outputStream = new FileOutputStream(cheminImg+nomFichier); 
			byte[] buffer = new byte[4096];          
			int bytesRead = 0;  
			while(true) {                          
				bytesRead = inputStream.read(buffer);  
				if(bytesRead > 0) {  
					outputStream.write(buffer, 0, bytesRead);  
				}else {  
					break;  
				}                         
			}  
			banniere.setImage(nomFichier);
			if(isParAppel())
				banniere.setTarif(tarifDAO.tarifAppelEnvigueur());
			if(isParNotification())
				banniere.setTarifNotification(tarifDAO.tarifNotificationEnvigueur());;

			for(String check: domainesSelected){
				Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
				banniere.addDomaine(d);
				d=new Domaine();
			}
			campagne.addPublicite(banniere);
			campagneDAO.modifier(campagne);
			banniere=new Banniere();
			domainesSelected.clear();
			outputStream.close();  
			inputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  

	}


	//edition publicite banniere
	public void editPubBanniere(){
		try {					
			String cheminImg=TrouverChemin.cheminImg();
			if(fichier==null){
				if(domainesSelected.size() > banniere.getDomaines().size()){
					for(String check: domainesSelected){
						Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
						banniere.addDomaine(d);
						d=new Domaine();
					}
				}			
				pubDAO.modifier(banniere);
			}
			else{
				InputStream inputStream = fichier.getInputStream(); 
				String nomFichier=Generateur.generateRandomString(18, Mode.ALPHANUMERIC).toUpperCase()+"."+getFileExtension(fichier);
				FileOutputStream outputStream = new FileOutputStream(cheminImg+nomFichier); 
				byte[] buffer = new byte[4096];          
				int bytesRead = 0;  
				while(true) {                          
					bytesRead = inputStream.read(buffer);  
					if(bytesRead > 0) {  
						outputStream.write(buffer, 0, bytesRead);  
					}else {  
						break;  
					}                         
				}  
				banniere.setImage(nomFichier);
				if(domainesSelected.size() > banniere.getDomaines().size()){
					for(String check: domainesSelected){
						Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
						banniere.addDomaine(d);
						d=new Domaine();
					}
				}		
				pubDAO.modifier(banniere);
				outputStream.close();  
				inputStream.close();
			}
			setLesCampagnes(campagneDAO.lister());
			domainesSelected.clear();
			banniere=new Banniere();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("detailCamp.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//edition publicite textuelle
	public String editPubTextuelle(){
		if(domainesSelected.size() > textuelle.getDomaines().size()){
			for(String check: domainesSelected){
				Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
				textuelle.addDomaine(d);
				d=new Domaine();
			}
		}	
		pubDAO.modifier(textuelle);
		setLesCampagnes(campagneDAO.lister());
		textuelle=new Textuelle();
		return null;
	}


	//chargement publicite banniere
	public void loadPubBanniere(Banniere banni){
		setBanniere(banni);	
		for(Domaine d: banni.getDomaines()){
			domainesSelected.add(String.valueOf(d.getId()));
		}
		ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("campagne/editPubBanniere.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//chargement publicite textuelle
	public String loadPubTextuelle(Textuelle texte){
		setTextuelle(texte);
		for(Domaine d: texte.getDomaines()){
			domainesSelected.add(String.valueOf(d.getId()));
		}
		return "pretty:editPubTexte";

	}


	//ajout de la campagne
	public void addCampagne(){
		System.out.println("ds add campagne");
		Client cli=getLastClient();
		campagne.setClient(cli);
		campagneDAO.creer(campagne);
		setShowPubMenu(true);
	}


	//chargement de la campagne en vue d'un règlement
	public String chargementReg(Campagne camp){
		setCampagne(camp);
		return "pretty:listeReg";
	}


	// mise a jour de la campagne
	public void editionCamp(){
		if(!(campagne.getDateFin().compareTo(new Date())<0) ){
			campagneDAO.modifier(campagne);
			setLesCampagnes(campagneDAO.lister());
			campagne=new Campagne();
			setShowEditCamp(false);
		}

	}


	//chargement de la campagne pour voir les details
	public String chargementCamp(Campagne campagne){
		setCampagne(campagne);	
		pubBaniere=new ArrayList<Publicite>();
		pubTextuelle=new ArrayList<Publicite>();
		for(Publicite p: campagne.getPublicites()){
			try {
				if(((Textuelle)p).getContenu()!=null){
					pubTextuelle.add(p);
				}
			} catch (Exception e) {
				pubBaniere.add(p);
			}
		}
		return "pretty:detailcamp";
	}


	//chargement des pubs pour les detail
	public String chargementPubTexte(Textuelle texte){
		setTextuelle(texte);
		return "pretty:detailPubTexte";
	}
	public void chargementPubBanni(Banniere bann){
		setBanniere(bann);
		ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("campagne/detailPubBanniere.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "pretty:detailPubBan";
	}


	// ajout ds les differentes liste de campagne
	public void initListe(){
		setLesCampagnes(campagneDAO.lister());
		if(campListe.equals("attente")){
			listeCamp=new ArrayList<Campagne>();
			for(Campagne ca:lesCampagnes){
				if(ca.getReglements().size()==0){
					listeCamp.add(ca);
				}
			}
		}

		if(campListe.equals("encours")){
			listeCamp=new ArrayList<Campagne>();
			for(Campagne ca:lesCampagnes){
				if(((ca.getDateFin().compareTo(new Date()) >0) || (ca.getDateFin().compareTo(new Date()) ==0))
						&& (ca.getReglements().size()>0)){
					listeCamp.add(ca);
				}
			}
		}

		if(campListe.equals("termine")){
			listeCamp=new ArrayList<Campagne>();
			for(Campagne ca:lesCampagnes){
				if((ca.getDateFin().compareTo(new Date())<0) && (ca.getReglements().size()>0) ){
					listeCamp.add(ca);
				}
			}
		}
	}


	//ajoutr les reglememts
	public void addReglement(){
		System.out.println("ds addregle");
		campagne.addReglement(reglement);
		campagneDAO.modifier(campagne);
	}


	// Extraction de l'extention du fichier depuis le content-disposition header
	private String getFileExtension(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String nomFichier=content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
				System.out.println( );
				return nomFichier.substring(nomFichier.length()-3);
			}
		}
		return null;
	}


	// ajout de domaine
	public void addDomaine(){
		if(current==null){
			pubDAO.creer(domaine);
			domaine=new Domaine();
		}
		else{
			pubDAO.modifier(current);
			domaine=new Domaine();
		}
		setDomaines(pubDAO.listerDomaine());
	}


	//edition  domaine
	public void editDomaine(Domaine dom){
		setDomaine(dom);
		setCurrent(domaine);
	}

	
	public void annuler(){
		campagne=new Campagne();
		textuelle=new Textuelle();
		banniere=new Banniere();
		domaine=new Domaine();
		campagneDAO=new CampagneDaoimpl();
		pubDAO=new PubliciteDaoImpl();
		tarifDAO=new TarifDaoImpl();
		reglement=new Reglement();
	}

	//recuperer le dernier client enregistrer
	private Client getLastClient(){
		setClientDAO(new ClientDaoImpl());
		Client c=clientDAO.findLastRecord();
		System.out.println(c.getId());
		return c;
	}
	
	
	/**
	 * 
	 * Getters et Setters
	 */

	public CampagneDAO getCampagneDAO() {
		return campagneDAO;
	}

	public boolean isShowEditCamp() {
		return showEditCamp;
	}


	public void setShowEditCamp(boolean showEditCamp) {
		this.showEditCamp = showEditCamp;
	}




	public void setCampagneDAO(CampagneDAO campagneDAO) {
		this.campagneDAO = campagneDAO;
	}

	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}



	public Part getFichier() {
		return fichier;
	}


	public void setFichier(Part fichier) {
		this.fichier = fichier;
	}


	public List<Campagne> getLesCampagnes() {
		return lesCampagnes;
	}


	public void setLesCampagnes(List<Campagne> lesCampagnes) {
		this.lesCampagnes = lesCampagnes;
	}



	public Reglement getReglement() {
		return reglement;
	}


	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}


	public List<String> getDomainesSelected() {
		return domainesSelected;
	}


	public void setDomainesSelected(List<String> domainesSelected) {
		this.domainesSelected = domainesSelected;
	}


	public List<Domaine> getDomaines() {
		return domaines;
	}


	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}


	public Textuelle getTextuelle() {
		return textuelle;
	}


	public void setTextuelle(Textuelle textuelle) {
		this.textuelle = textuelle;
	}


	public PubliciteDAO getPubDAO() {
		return pubDAO;
	}


	public void setPubDAO(PubliciteDAO pubDAO) {
		this.pubDAO = pubDAO;
	}

	public Banniere getBanniere() {
		return banniere;
	}


	public void setBanniere(Banniere banniere) {
		this.banniere = banniere;
	}




	public boolean isShowPubMenu() {
		return showPubMenu;
	}


	public void setShowPubMenu(boolean showPubMenu) {
		this.showPubMenu = showPubMenu;
	}




	public List<Publicite> getPubTextuelle() {
		return pubTextuelle;
	}




	public void setPubTextuelle(List<Publicite> pubTextuelle) {
		this.pubTextuelle = pubTextuelle;
	}




	public List<Publicite> getPubBaniere() {
		return pubBaniere;
	}




	public void setPubBaniere(List<Publicite> pubBaniere) {
		this.pubBaniere = pubBaniere;
	}




	public Domaine getDomaine() {
		return domaine;
	}




	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}




	public Domaine getCurrent() {
		return current;
	}

	public List<Campagne> getListeCamp() {
		return listeCamp;
	}




	public void setListeCamp(List<Campagne> listeCamp) {
		this.listeCamp = listeCamp;
	}




	public void setCurrent(Domaine current) {
		this.current = current;
	}


	public String getCampListe() {
		return campListe;
	}




	public void setCampListe(String campListe) {
		this.campListe = campListe;
	}




	public TarifDAO getTarifDAO() {
		return tarifDAO;
	}




	public void setTarifDAO(TarifDAO tarifDAO) {
		this.tarifDAO = tarifDAO;
	}




	public ClientDAO getClientDAO() {
		return clientDAO;
	}




	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}




	public boolean isParAppel() {
		return parAppel;
	}




	public void setParAppel(boolean parAppel) {
		this.parAppel = parAppel;
	}




	public boolean isParNotification() {
		return parNotification;
	}




	public void setParNotification(boolean parNotification) {
		this.parNotification = parNotification;
	}















}
