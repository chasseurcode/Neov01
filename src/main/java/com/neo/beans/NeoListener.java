package com.neo.beans;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import com.neo.utility.HibernateUtil;

public class NeoListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Arret du listeneur");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("Indexation des données existante");
		FullTextSession ftSession=Search.getFullTextSession(HibernateUtil.getSession());
		try {
			ftSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}