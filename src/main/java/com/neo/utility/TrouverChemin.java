package com.neo.utility;

public class TrouverChemin {
	

	public static String cheminImg(){
	String chemin;
	ClassLoader classLoader;
    classLoader=TrouverChemin.class.getClassLoader();
	chemin=classLoader.getResource("../../resources/pubs").getPath();
		return chemin;
	}

}
