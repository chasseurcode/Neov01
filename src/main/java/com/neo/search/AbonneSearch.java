package com.neo.search;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neo.domaine.Abonne;
import com.neo.utility.HibernateUtil;

public class AbonneSearch extends NeoSearch {
	public AbonneSearch() {
		setNomEntite(Abonne.class.getName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List chercher(String requete) {
		FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSession()); 
		QueryBuilder builder = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Abonne.class).get();
		org.apache.lucene.search.Query luceneQuery =
			    builder.keyword()
			        .onFields("prenom","dateDeNaissance","codeParrainege","codeFilleule")
			        .ignoreFieldBridge()
			        .matching(requete)
			        .createQuery();
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		List resultat = fullTextQuery.list();
		return resultat;
	}
}
