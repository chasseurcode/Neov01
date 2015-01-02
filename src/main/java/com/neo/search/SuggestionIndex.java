package com.neo.search;

import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.LevensteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.hibernate.search.annotations.Indexed;
import org.reflections.Reflections;

public class SuggestionIndex {
	private Logger logger=Logger.getLogger(getClass().getName());
	private String dirSpellCheck;
	private String dirIndex;
	private Properties properties;
	private static SuggestionIndex INSTANCE=null;
	private SuggestionIndex() {
		properties=new Properties();
		try {
			InputStream inStream=new FileInputStream(getClass().getResource("../../../")
															   .getPath()+"searconf.properties");
			properties.load(inStream);
			dirIndex=properties.get("RepIndex").toString();
			dirSpellCheck=properties.get("DirSugg").toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized SuggestionIndex getInstance() {
		if(INSTANCE==null)
			return new SuggestionIndex();

		return INSTANCE;
	}

	public void indexAllClass()  {
		logger.info("Debut d'indexation NEOSearch Suggestion");
		Reflections reflection=new Reflections("com.neo.domaine");
		Set<Class<? extends Object>> listClass=reflection.getTypesAnnotatedWith(Indexed.class);
		for(Class<?> maClass: listClass){
			indeOneEntity(maClass);
		}
		logger.info("Fin de l'indexation NEOSearch Suggession");
	}
	
	/*
	 * Indexation des champs d'une entité
	 */
	@SuppressWarnings("unchecked")
	public void indeOneEntity(Class<?> maClass) {
		Set<Field> champs = getAllFields(maClass, withAnnotation(org.hibernate.search.annotations.Field.class));
		for(Field champ: champs){
			logger.info(maClass.getName());
			indexerChamp(maClass, champ);	
		}
	}
	
	/*
	 * Permet d'indexer un champ dans une classe
	 */
	@SuppressWarnings("resource")
	public void indexerChamp(Class<?> maClass, Field champ) {
		logger.info(maClass.getName()+" : "+champ.getName());
		IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_36,new StandardAnalyzer(Version.LUCENE_36));
		Directory repSugg;
		try {
			repSugg = FSDirectory.open(new File(dirSpellCheck));
			SpellChecker mSpell=new SpellChecker(repSugg);
			Directory indexRep=FSDirectory.open(new File(dirIndex+maClass.getName()));
			IndexReader reader=IndexReader.open(indexRep);
			try{
				mSpell.indexDictionary(new LuceneDictionary(reader, champ.getName()), conf, true);
			}finally{
				reader.close();
			}
			indexRep.close();
			repSugg.close();
		} catch (IOException e) {
			logger.warning("Erreur d'accès au repertoire "+e.getMessage());
		}
	}


	@SuppressWarnings("resource")
	public String[] getSuggestions(String motRechercher,int SuggNbr){
		Directory rep;
		try {
			rep = FSDirectory.open(new File(dirSpellCheck));

			if(!IndexReader.indexExists(rep)){
				logger.warning("Erreur d'accès au repertoire "+dirSpellCheck);
				return null;
			}else{
				SpellChecker mSpell=new SpellChecker(rep);
				mSpell.setStringDistance(new LevensteinDistance());
				return mSpell.suggestSimilar(motRechercher, SuggNbr);
			}
		} catch (IOException e) {
			logger.warning("impossible d'accédé à "+dirSpellCheck);
		}
		return null;
	}

}
