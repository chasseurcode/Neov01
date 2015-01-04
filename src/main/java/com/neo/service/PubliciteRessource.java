package com.neo.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neo.dao.PubliciteDAO;
import com.neo.daoImpl.PubliciteDaoImpl;
import com.neo.domaine.Banniere;
import com.neo.domaine.Textuelle;


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
}
