package com.neo.utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.neo.domaine.Permission;
import com.neo.domaine.Role;
import com.neo.domaine.Utilisateur;

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
