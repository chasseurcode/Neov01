package com.neo.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.neo.dao.AbonneeDAO;
import com.neo.daoImpl.AbonneDAOImpl;
import com.neo.domaine.Abonne;
import com.neo.utility.Generateur;
import com.neo.utility.Generateur.Mode;

@Path("/abonnes")
public class AbonneRessource {
	private AbonneeDAO daoAbonne;

	public AbonneRessource() {
		setDaoAbonne(new AbonneDAOImpl());
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getAbonne(@PathParam("id") String numAbonne) {
		Abonne abonne=daoAbonne.findById(new Long(numAbonne));
		if(abonne!=null){
			return Response.ok().entity(abonne).build();
		}
		return Response.status(404).entity("Pas d'abonné correspondant").build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbonne(Abonne abonne){
		if(abonne!=null){
			abonne.setMiseAJour(new Date());
			daoAbonne.modifier(abonne);
			return Response.status(201).entity(abonne).build();
		}
		else {
			return Response.status(406).entity(abonne).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response incription(Abonne abonne){
		try {
			if(abonne!=null){
				//Generer le compte
				String compte = "NEONE"+Generateur.generateRandomString(5, Mode.ALPHANUMERIC).toUpperCase();
				String motDePasse=Generateur.generateRandomString(8, Mode.ALPHANUMERIC);
				RandomNumberGenerator rng = new SecureRandomNumberGenerator();
				Object salt = rng.nextBytes();
				String hashPass = new Sha256Hash(motDePasse, salt,1024).toBase64();
				//Generer le code de parainnage
				String codeParain="NEONE"+Generateur.generateRandomString(6, Mode.ALPHANUMERIC).toUpperCase();
				
				abonne.setCompte(compte);
				abonne.setMotDePasse(hashPass);
				abonne.setSaltMotDePasse(salt.toString());
				abonne.setActif(true);
				abonne.setCodeParrainege(codeParain);
				//Sauvegarder l'abonné
				daoAbonne.creer(abonne);
				return Response.status(201).entity(abonne).build();
			}else {
				return Response.status(406).entity("Erreur lors de l'inscription").build();	
			}
						
		} catch (Exception e) {
			return Response.status(406).entity("Erreur lors de l'inscription").build();
		}
	}

	public AbonneeDAO getDaoAbonne() {
		return daoAbonne;
	}

	public void setDaoAbonne(AbonneeDAO daoAbonne) {
		this.daoAbonne = daoAbonne;
	}

}
