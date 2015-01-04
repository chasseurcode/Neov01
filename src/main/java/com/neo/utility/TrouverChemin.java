package com.neo.utility;

public class TrouverChemin {
	

	public static String cheminImg(){
	String chemin;
	ClassLoader classLoader;
    classLoader=TrouverChemin.class.getClassLoader();
	chemin=classLoader.getResource("../../WEB-INF/classes/pubs").getPath();

		return chemin;
	}

}
