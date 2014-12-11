package com.neo.utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.neo.domaine.Abonne;
import com.neo.domaine.Campagne;
import com.neo.domaine.Carte;
import com.neo.domaine.Client;
import com.neo.domaine.Depense;
import com.neo.domaine.Message;
import com.neo.domaine.Paiement;
import com.neo.domaine.Permission;
import com.neo.domaine.Publicite;
import com.neo.domaine.Reglement;
import com.neo.domaine.Role;
import com.neo.domaine.TarifAppel;
import com.neo.domaine.TarifNotification;
import com.neo.domaine.TarifTextuelle;
import com.neo.domaine.Utilisateur;
import com.neo.domaine.Vue;

public class HibernateUtil {
	private static Configuration conf;
	private static SessionFactory factory;
	private static Session session;
	private static SessionFactory getFactory(){
		if (factory == null){
			conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Utilisateur.class);
			conf.addAnnotatedClass(Role.class);
			conf.addAnnotatedClass(Permission.class);
			conf.addAnnotatedClass(Message.class);
			conf.addAnnotatedClass(Abonne.class);
			conf.addAnnotatedClass(Campagne.class);
			conf.addAnnotatedClass(Reglement.class);
			conf.addAnnotatedClass(Client.class);
			conf.addAnnotatedClass(Paiement.class);
			conf.addAnnotatedClass(Publicite.class);
			conf.addAnnotatedClass(Vue.class);
			conf.addAnnotatedClass(Carte.class);
			conf.addAnnotatedClass(Depense.class);
			conf.addAnnotatedClass(TarifAppel.class);
			conf.addAnnotatedClass(TarifNotification.class);
			conf.addAnnotatedClass(TarifTextuelle.class);

			conf.configure();
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			factory = conf.buildSessionFactory(ssrb.build());
		}
		return factory;
	}
	public static Session getSession(){
		if (session == null){
			session = getFactory().openSession();
		}
		return session;
	}
}
