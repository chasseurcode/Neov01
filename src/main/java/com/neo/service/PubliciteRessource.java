package com.neo.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neo.dao.PubliciteDAO;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Textuelle;

@Path("/")
public class PubliciteRessource {
	private PubliciteDAO pubDao=new PubliciteDaoImpl(); 

	@Path("bannieres")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Banniere> getBannieres() {
		//TODO: algorithme de distrib
		return pubDao.listerBanniere();
	}

	@Path("textuelles")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Textuelle> getTextuelles() {
		//TODO: algorithme de distrib
		return pubDao.listerTextuelle();
	}
	private class Pub {
		private String intitule;
		private Date dateDebut;
		private Date dateFin;
		private int vueAFaire=0;
		private int vueEffectue=0;

		public String getIntitule() {
			return intitule;
		}

		public void setIntitule(String intitule) {
			this.intitule = intitule;
		}

		public Date getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}

		public Date getDateFin() {
			return dateFin;
		}

		public int getVueAFaire() {
			return vueAFaire;
		}

		public void setVueAFaire(int vueAFaire) {
			this.vueAFaire = vueAFaire;
		}

		public int getVueEffectue() {
			return vueEffectue;
		}

		public void setVueEffectue(int vueEffectue) {
			this.vueEffectue = vueEffectue;
		}

		public boolean isExpired(){
			if ((vueAFaire==vueEffectue) || (dateFin.equals(new Date())))
				return true;
			return false;
		}

		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}
	}

	private class Ban extends Pub{
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
	}

	private class Texto extends Pub{
		private String contenu;

		public String getContenu() {
			return contenu;
		}

		public void setContenu(String contenu) {
			this.contenu = contenu;
		}	
	}


}
