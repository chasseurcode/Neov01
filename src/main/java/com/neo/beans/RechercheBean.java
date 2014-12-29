package com.neo.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.neo.domaine.Abonne;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Publicite;
import com.neo.search.NeoEngine;

@ManagedBean
@SessionScoped
public class RechercheBean {
	private String requete;
	private List<Client> clients;
	private List<Campagne> campagnes;
	private List<Publicite> publicites;
	private List<Abonne> abonnes;
	private List<String> suggestions;
	final String SUGG_PREFIX="sugg";
	@SuppressWarnings("rawtypes")
	private Map<String, List> resultat;
	private int nbrResultat;
	
	@PostConstruct
	public void init() {
		clients=new ArrayList<Client>();
		campagnes=new ArrayList<Campagne>();
		publicites=new ArrayList<Publicite>();
	}

	public String getRequete() {
		return requete;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}

	public void search() {
		nbrResultat=0;
		resultat=NeoEngine.chercher(requete);
		//Extraction du resultat
		extractResult();
		//recuperation des suggestions
		List<String> listSugg=new ArrayList<String>();
		for(String sugg: NeoEngine.suggestion(requete)){
			listSugg.add(sugg);
		}
		setSuggestions(listSugg);
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
		try {
			//redirection vers la page de recherche
			ctx.redirect(request.getContextPath()+"/recherche");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Relance la recherche avec la suggestion
	public void essaiSuggestion(String sugg) {
		setRequete(sugg);
		search();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void extractResult() {
		for (Entry<String, List> mResultat : resultat.entrySet())
		{
			if(mResultat.getKey().equalsIgnoreCase(Client.class.getName())){
				setClients(mResultat.getValue());
				nbrResultat=nbrResultat+mResultat.getValue().size();
			}

			if(mResultat.getKey().equalsIgnoreCase(Campagne.class.getName())){
				setCampagnes(mResultat.getValue());
				nbrResultat=nbrResultat+mResultat.getValue().size();
			}

			if(mResultat.getKey().equalsIgnoreCase(Publicite.class.getName())){
				setPublicites(mResultat.getValue());
				nbrResultat=nbrResultat+mResultat.getValue().size();
			}

			if(mResultat.getKey().equalsIgnoreCase(Abonne.class.getName())){
				setAbonnes(mResultat.getValue());
				nbrResultat=nbrResultat+mResultat.getValue().size();
			}

		}
	}

	
	/*
	 * Getter and Setter
	 * 
	 */
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, List> getResultat() {
		return resultat;
	}

	@SuppressWarnings("rawtypes")
	public void setResultat(Map<String, List> resultat) {
		this.resultat = resultat;
	}

	public int getNbrResultat() {
		return nbrResultat;
	}

	public void setNbrResultat(int nbrResultat) {
		this.nbrResultat = nbrResultat;
	}

	public List<Campagne> getCampagnes() {
		return campagnes;
	}

	public void setCampagnes(List<Campagne> campagnes) {
		this.campagnes = campagnes;
	}

	public List<Publicite> getPublicites() {
		return publicites;
	}

	public void setPublicites(List<Publicite> publicites) {
		this.publicites = publicites;
	}

	public List<Abonne> getAbonnes() {
		return abonnes;
	}

	public void setAbonnes(List<Abonne> abonnes) {
		this.abonnes = abonnes;
	}

	public List<String> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}

}
