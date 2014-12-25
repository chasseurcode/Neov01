package com.neao.dao.test;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neo.domaine.Client;
import com.neo.utility.HibernateUtil;


public class Searchtest {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		//recuperation de la session
		FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSession()); 
		QueryBuilder builder = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Client.class).get();
		org.apache.lucene.search.Query luceneQuery =
			    builder.keyword()
			        .onFields("nom","adresse","raisonSociale")
			        .matching("19")
			        .createQuery();
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		List<Client> result = fullTextQuery.list();
		System.out.println("termine :"+result.size());
		for(Client c: result){
			System.out.println(c.getNom());
			System.out.println(c.getAdresse());
			System.out.println("-----------------------");
		}
	}
}
