package com.neo.dao;

import java.util.List;

import com.neo.domaine.Publicite;

public interface PubliciteDAO {

	public void creer(Publicite publicite);
	public void modifier(Publicite publicite);
	public void supprimer(Publicite publicite);
	public Publicite findById(int id);
	public List<Publicite> lister();
}
