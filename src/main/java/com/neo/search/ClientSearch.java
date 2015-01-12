package com.neo.search;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neo.domaine.Client;
import com.neo.utility.HibernateUtil;



public class ClientSearch extends NEOSearch {
	public ClientSearch() {
		setNomEntite(Client.class.getName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List chercher(String requete) {
		System.out.println(requete);
		FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSession()); 
		QueryBuilder builder = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Client.class).get();
		
		org.apache.lucene.search.Query luceneQuery =
			    builder.keyword()
			        .onFields("nom","adresse","raisonSociale")
			        .matching(requete)
			        .createQuery();
		
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		List resultat = fullTextQuery.list();
		System.out.println(resultat.size());
		return resultat;
	}

}
