package com.neo.utility;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import com.neo.search.DemonSuggestion;

public class NEOListener implements ServletContextListener{
	private Logger logger=Logger.getLogger(getClass().getName());
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Arret du listener");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Indexation des données existante");
		FullTextSession ftSession=Search.getFullTextSession(HibernateUtil.getSession());
		try {
			
			//indexation des données déjà presente dans la base
			ftSession.createIndexer().startAndWait();
			
			//Lancer le demon pour l'indexation chaque 1h
			new Thread(new DemonSuggestion(1000*60*60*60*24)).start();
						
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}