package com.neo.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.neo.domaine.Abonne;

@Path("/abonnes")
public class AbonneRessource {
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getAbonne(@PathParam("id") String numAbonne) {
		Abonne abonne=new Abonne();
		abonne.setCompte("Ali");
		abonne.setPrenom("Mohamed");
		abonne.setEmail("mhAli@gmail.com");
		abonne.setTelehone(numAbonne);
		abonne.setMotDePasse("carcasson");
		abonne.setActif(true);
		return Response.ok().entity(abonne).build();
	}
	
	
	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response incription(Abonne abonne){
			
			System.out.println(abonne);
			return Response.status(201).entity(abonne).build();
	}
	
}
