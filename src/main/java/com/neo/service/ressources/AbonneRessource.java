package com.neo.service.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/abonnes")
public class AbonneRessource {
	@Path("/pubs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getPublicite() {
		Pub pub=new  Pub("Astec", "Maaya");
		Gson gson=new Gson();
		return Response.ok(gson.toJson(pub)).build();
	}
	
	class Pub{
		private String nom;
		private String prenom;
		
		public Pub() {
			
		}
		
		public Pub(String nom, String prenom) {
			super();
			this.nom = nom;
			this.prenom = prenom;
		}

		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		
	}
}
