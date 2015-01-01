package com.neo.search;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.type.Type;
import org.reflections.Reflections;

public class NEOInterceptor extends EmptyInterceptor {
	
	private static final long serialVersionUID = 1L;
	
	private SuggestionIndex suggession;
	private Object entite=null;
	
	public NEOInterceptor() {
		suggession=SuggestionIndex.getInstance();
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		verifyAndIndex(entity);
		return super.onFlushDirty(entity, id, currentState, previousState,propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		verifyAndIndex(entity);
		return super.onSave(entity, id, state, propertyNames, types);
	}	

	private void verifyAndIndex(Object entity) {
		setEntite(entity);
		Reflections reflection=new Reflections("com.neo.domaine");
		Set<Class<? extends Object>> listIndexer=reflection.getTypesAnnotatedWith(Indexed.class);
		if(listIndexer.contains(entity.getClass())){
			//lancer l'indexation de l'entité dans un thread separé
			new Thread(new Runnable() {
				@Override
				public void run() {
					suggession.indeOneEntity(entite.getClass());
				}
			}).start();
		}
	}

	public Object getEntite() {
		return entite;
	}

	public void setEntite(Object entite) {
		this.entite = entite;
	}

}
