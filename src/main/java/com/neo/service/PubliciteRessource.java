package com.neo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.neo.dao.AbonneeDAO;
import com.neo.dao.PubliciteDAO;
import com.neo.dao.VueDao;
import com.neo.daoImpl.AbonneDAOImpl;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.daoImpl.VueDaoImpl;
import com.neo.domaine.Abonne;
import com.neo.domaine.Banniere;
import com.neo.domaine.Publicite;
import com.neo.domaine.Textuelle;
import com.neo.domaine.Vue;
import com.neo.utility.TrouverChemin;

@Path("/")
public class PubliciteRessource {
	private PubliciteDAO pubDao=new PubliciteDaoImpl(); 
	private VueDao daoVue=new VueDaoImpl();
	private AbonneeDAO abnDao=new AbonneDAOImpl();
	
	@Path("gains")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGain(Pub pub){
		Vue vue=new Vue();
		Subject sujet=SecurityUtils.getSubject();
		Publicite publicite=pubDao.findBanniereById(pub.getPubId());
		Abonne abonne=abnDao.findByCompte(sujet.getPrincipal().toString());
		if(pub.getVueEffectue()>0){
			vue.setPublicite(publicite);
			vue.setAbonne(abonne);
			vue.setNbreVue(pub.getVueEffectue());
			vue.setGain((float) (pub.getVueEffectue()*pub.getPrixAuvue()));
			System.out.println(vue.getGain()+" - "+vue.getNbreVue());
			daoVue.creer(vue);
		}
		
		return Response.ok().build();
	}
	
	@Path("bannieres")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ban> getBannieres() {
		//TODO: algorithme de distrib
		return listBannieres(pubDao.listerBanniere());
	}
	
	@GET
	@Path("bannieres/{name}")
	@Produces("image/jpg")
	public Response loadImage(@PathParam("name") String imgName) {
		System.out.println(imgName);
		File fichier=new File(TrouverChemin.cheminImg()+imgName);
		ResponseBuilder response = Response.ok((Object) fichier);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		response.header("Content-Disposition", "attachment; filename=image_from_server.png");
		
		return response.build();
	}
	

	@Path("textuelles")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Texto> getTextuelles() {
		//TODO: algorithme de distrib
		return listTexto(pubDao.listerTextuelle());
	}
	
	public List<Ban> listBannieres(List<Banniere> banList) {
		List<Ban> bans=new ArrayList<PubliciteRessource.Ban>();
		for(Banniere b: banList){
			Ban mBanierre=new Ban();
			mBanierre.setPubId(b.getId());
			mBanierre.setDateFin(b.getDateFin());
			mBanierre.setImage(b.getImage());
			mBanierre.setIntitule(b.getIntitule());
			mBanierre.setVueAFaire(2);
			mBanierre.setVueEffectue(0);
			mBanierre.setPrixAuvue(0.6);
			bans.add(mBanierre);
		}
		return bans;
	}
	
	public List<Texto> listTexto(List<Textuelle> textuelles) {
		List<Texto> mesTexto=new ArrayList<PubliciteRessource.Texto>();
		for(Textuelle t: textuelles){
			Texto txto=new Texto();
			txto.setPubId(t.getId());
			txto.setIntitule(t.getIntitule());
			txto.setContenu(t.getContenu());
			txto.setDateFin(t.getDateFin());
			txto.setVueAFaire(1);
			txto.setVueEffectue(0);
			txto.setPrixAuvue(0.5);
			mesTexto.add(txto);
		}
		return mesTexto;
	}
	
	
	/**
	 * Class utilitaires
	 */
	@XmlRootElement
	public static class Pub {
		private Long pubId;
		private String intitule;
		private Date dateFin;
		private double prixAuvue;
		private int vueAFaire=0;
		private int vueEffectue=0;
		
		
		public Long getPubId() {
			return pubId;
		}

		public void setPubId(Long id) {
			this.pubId = id;
		}

		public String getIntitule() {
			return intitule;
		}

		public void setIntitule(String intitule) {
			this.intitule = intitule;
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

		public double getPrixAuvue() {
			return prixAuvue;
		}

		public void setPrixAuvue(double prixAuvue) {
			this.prixAuvue = prixAuvue;
		}
		@Override
		public String toString() {
			return pubId+" - "+vueEffectue;
		}
	}

	@XmlRootElement
	public static class Ban extends Pub{
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
	}

	@XmlRootElement
	public static class Texto extends Pub{
		private String contenu;

		public String getContenu() {
			return contenu;
		}

		public void setContenu(String contenu) {
			this.contenu = contenu;
		}	
	}

}
