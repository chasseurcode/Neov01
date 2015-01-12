package com.neo.dao;

import java.util.List;

import com.neo.domaine.Banniere;
import com.neo.domaine.Domaine;
import com.neo.domaine.Publicite;
import com.neo.domaine.Textuelle;

public interface PubliciteDAO {

	public void creer(Banniere banniere);
	public void creer(Textuelle textuelle);
	public void modifier(Banniere banniere);
	public void modifier(Textuelle textuelle);
	public void supprimer(Banniere banniere);
	public void supprimer(Textuelle textuelle);
	public Banniere findBanniereById(long id);
	public Textuelle findTextuelleById(long id);
	public List<Banniere> listerBanniere();
	public List<Textuelle> listerTextuelle();
	public void creer(Domaine domaine);
	public void modifier(Domaine domaine);
	public void supprmer(Domaine domaine);
	public Domaine findDomaineById(long id);
	public List<Domaine> listerDomaine();
	public Publicite findPubliciteById(long id);
	
}
