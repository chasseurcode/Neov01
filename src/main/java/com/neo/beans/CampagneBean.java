package com.neo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import com.neo.dao.CampagneDAO;
import com.neo.daoImpl.CampagneDaoimpl;
import com.neo.domaine.Campagne;
import com.neo.domaine.Publicite;
import com.neo.domaine.Reglement;

@ManagedBean
@SessionScoped
public class CampagneBean {

	private boolean showPub=false;
	private Part fichier;
	private CampagneDAO campagneDAO;
	private Campagne campagne;
	private Publicite publicite;
	private Reglement reglement;
	private List<Publicite> lesPublicites;
	private List<Campagne> lesCampagnes;
	private List<Campagne> lesCampagnesEncours, lesCampagnesEnAttentes,lesCampagnesterminees;
	private String datefin,datedebut;


	public CampagneBean() {
		campagne=new Campagne();
		campagneDAO=new CampagneDaoimpl();
		publicite=new Publicite();
		reglement=new Reglement();
		lesPublicites=new ArrayList<Publicite>();
		lesCampagnesEnAttentes=new ArrayList<Campagne>();
		lesCampagnesEncours=new ArrayList<Campagne>();
		lesCampagnesterminees=new ArrayList<Campagne>();
		setLesCampagnes(campagneDAO.lister());
		initListe();
	}


	// affichage du formulaire des pubs
	public String afficherPub(Campagne campagne) {
		if(showPub){
			setShowPub(false);
		}
		else{
			setShowPub(true);
		}
		setCampagne(campagne);
		return null;
	}

	//ajout de la publicite
	public void addPublicite(){
		System.out.println("ds addpub");

		for(String check: domainesSelected){
			Domaine d=pubDAO.findDomaineById(Long.parseLong(check));
			textuelle.addDomaine(d);
			d=new Domaine();
		}
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

	}

	//ajout de la campagne
	public void addCampagne(){
		System.out.println("ds add campagne");
	}

	//chargement de la campagne en vue d'un règlement
	public String chargementReg(Campagne camp){
		System.out.println("reglement");
		setCampagne(camp);
		return "pretty:listeReg";
	}

	// mise a jour de la campagne
	public void editionCamp(){
		campagneDAO.modifier(campagne);
		setShowPub(false);
	}

	//chargement de la campagne pour voir les details
	public String chargementCamp(Campagne campagne){
		setCampagne(campagne);	
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
	private void initListe(){
		for(Campagne c:lesCampagnes){
			if((c.getDateFin().compareTo(new Date())<0) && (c.getReglements().size()>0) ){
				lesCampagnesterminees.add(c);
			}
			if(((c.getDateFin().compareTo(new Date()) >0) || (c.getDateFin().compareTo(new Date()) ==0))
					&& (c.getReglements().size()>0)){
				lesCampagnesEncours.add(c);
			}
			if(c.getReglements().size()==0){
				lesCampagnesEnAttentes.add(c);
			}
		}
	}

	//ajoutr les reglememts
	public void addReglement(){
		System.out.println("ds addregle");
		campagne.addReglement(reglement);
		campagneDAO.modifier(campagne);
	}


	
	/**
	 * 
	 * Getters et Setters
	 */

	public boolean isShowPub() {
		return showPub;
	public CampagneDAO getCampagneDAO() {
		return campagneDAO;
	}

	public void setShowPub(boolean showPub) {
		this.showPub = showPub;
	}

	public CampagneDAO getCampagneDAO() {
		return campagneDAO;
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

	public Publicite getPublicite() {
		return publicite;
	}

	public void setPublicite(Publicite publicite) {
		this.publicite = publicite;
	}

	public List<Publicite> getLesPublicites() {
		return lesPublicites;
	}

	public void setLesPublicites(List<Publicite> lesPublicites) {
		this.lesPublicites = lesPublicites;
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


	public List<Campagne> getLesCampagnesEncours() {
		return lesCampagnesEncours;
	}


	public void setLesCampagnesEncours(List<Campagne> lesCampagnesEncours) {
		this.lesCampagnesEncours = lesCampagnesEncours;
	}


	public List<Campagne> getLesCampagnesEnAttentes() {
		return lesCampagnesEnAttentes;
	}


	public void setLesCampagnesEnAttentes(List<Campagne> lesCampagnesEnAttentes) {
		this.lesCampagnesEnAttentes = lesCampagnesEnAttentes;
	}


	public List<Campagne> getLesCampagnesterminees() {
		return lesCampagnesterminees;
	}


	public void setLesCampagnesterminees(List<Campagne> lesCampagnesterminees) {
		this.lesCampagnesterminees = lesCampagnesterminees;
	}


	public Reglement getReglement() {
		return reglement;
	}


	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}


	public String getDatefin() {
		return datefin;
	}


	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}


	public String getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}







}
