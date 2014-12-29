package com.neo.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
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

import com.neo.domaine.Abonne;
import com.neo.domaine.Campagne;
import com.neo.domaine.Client;
import com.neo.domaine.Reglement;

public class IndexeChecker {
	private Logger logger=Logger.getLogger(getClass().getName());
	private String dirSpellCheck;
	private String dirIndex;
	private HashMap<String, List<String>> listEntityAIndexer=new HashMap<String, List<String>>();
	private Properties properties;
	private static IndexeChecker INSTANCE=null;
	private IndexeChecker() {
		//initialiser les champs à indexer
		initEntityList();
		properties=new Properties();
		try {
			InputStream inStream=new FileInputStream(getClass().getResource("../../../").getPath()+"searconf.properties");
			properties.load(inStream);
			dirIndex=properties.get("RepIndex").toString();
			dirSpellCheck=properties.get("DirSugg").toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized IndexeChecker getInstance() {
		if(INSTANCE==null)
			return new IndexeChecker();

		return INSTANCE;
	}

	@SuppressWarnings("resource")
	public void lancerIndexation()  {
		logger.info("Debut d'indexation NEOSearch Suggestion");
			
		for (Entry<String, List<String>> champs : listEntityAIndexer.entrySet()) {
			logger.info("Indexation de "+champs.getKey());
			for(String champ: champs.getValue()){
				IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_36,new StandardAnalyzer(Version.LUCENE_36));
				Directory repSugg;
				try {
					repSugg = FSDirectory.open(new File(dirSpellCheck));
					SpellChecker mSpell=new SpellChecker(repSugg);
					Directory indexRep=FSDirectory.open(new File(dirIndex+champs.getKey()));
					logger.info(champs.getKey()+" : "+champ);
					IndexReader reader=IndexReader.open(indexRep);
					try{
						mSpell.indexDictionary(new LuceneDictionary(reader, champ), conf, true);
					}finally{
						reader.close();
					}
					indexRep.close();
					repSugg.close();
				} catch (IOException e) {
					logger.warning("Erreur d'accès au repertoire "+e.getMessage());
				}
			}
		}
		logger.info("Fin de l'indexation NEOSearch Suggession");
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

	private void initEntityList() {
		
		//Index champs client
		List<String> attClient=new ArrayList<String>();
		attClient.add("nom");
		attClient.add("adresse");
		attClient.add("raisonSociale");
		attClient.add("email");
		attClient.add("telehone");
		listEntityAIndexer.put(Client.class.getName(), attClient);
		
		
		//Index champs Abonné
		List<String> attAbonne=new ArrayList<String>();
		attAbonne.add("nom");
		attAbonne.add("prenom");
		attAbonne.add("dateDeNaissance");
		attAbonne.add("codeParrainege");
		attAbonne.add("codeFilleule");
		attAbonne.add("email");
		attAbonne.add("telehone");
		attAbonne.add("adresse");
		listEntityAIndexer.put(Abonne.class.getName(), attAbonne);
		
		//Index champs Campagne
		List<String> attCamp=new ArrayList<String>();
		attCamp.add("intitule");
		attCamp.add("dateDebut");
		attCamp.add("dateFin");
		attCamp.add("dateCreation");
		listEntityAIndexer.put(Campagne.class.getName(), attCamp);
		
		//Index champs Reglement
		List<String> attReglem=new ArrayList<String>();
		attReglem.add("dateReglement");
		attReglem.add("type");
		attReglem.add("montant");
		listEntityAIndexer.put(Reglement.class.getName(), attReglem);
	}
	
}
