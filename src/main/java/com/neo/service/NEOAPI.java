package com.neo.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class NEOAPI extends javax.ws.rs.core.Application{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(AbonneRessource.class);
		s.add(MessageRessource.class);
		return s;
	}
}
