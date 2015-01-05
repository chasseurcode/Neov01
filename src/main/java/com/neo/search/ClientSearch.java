package com.neo.search;

import java.util.List;
import java.util.Map.Entry;

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
	protected void chercher(String requete) {
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
		NEOSearch suivant=getSuivant();
		if(suivant!=null){
			for (Entry<String, List> mResultat : getResultat().entrySet())
			{
				suivant.getResultat().put(mResultat.getKey(), mResultat.getValue());
			}
			getResultat().put(getNomEntite(), resultat);
		}else{
			getResultat().put(getNomEntite(), resultat);
		}
		
	}

}
