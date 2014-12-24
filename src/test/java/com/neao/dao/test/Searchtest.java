package com.neao.dao.test;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neo.domaine.Utilisateur;
import com.neo.utility.HibernateUtil;

public class Searchtest {
	public static void main(String args[]) {
		//recuperation de la session
		FullTextSession ftSession=Search.getFullTextSession(HibernateUtil.getSession());
		
		QueryBuilder qb=ftSession.getSearchFactory().buildQueryBuilder()
								.forEntity(Utilisateur.class)
								.get();
		Query luceneQuery=qb.keyword().onField("compte").matching("Mohamed").createQuery();
		
		org.hibernate.Query hbQuery=ftSession.createFullTextQuery(luceneQuery, Utilisateur.class);
		List<Utilisateur> utilisateurs=hbQuery.list();
		for(Utilisateur u:utilisateurs){
			System.out.println(u.getCompte());
		}
	}
}
