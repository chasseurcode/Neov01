package com.neo.search;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neo.domaine.Campagne;
import com.neo.utility.HibernateUtil;


public class CampagneSearch extends NeoSearch {
	public CampagneSearch() {
		setNomEntite(Campagne.class.getName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List chercher(String requete) {
		FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSession()); 
		QueryBuilder builder = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Campagne.class).get();
		org.apache.lucene.search.Query luceneQuery =
			    builder.keyword()
			        .onFields("intitule","dateDebut","dateFin","dateCreation")
			        .ignoreFieldBridge()
			        .matching(requete)
			        .createQuery();
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		List resultat = fullTextQuery.list();
		
		return resultat;
	}
	
}
