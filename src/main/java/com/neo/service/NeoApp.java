package com.neo.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import com.neo.service.ressources.AbonneRessource;

@ApplicationPath("/api/v1")
public class NeoApp extends javax.ws.rs.core.Application{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(AbonneRessource.class);
		return s;
	}
}
