package com.maurrysonn.curling_tools.core.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtils {

	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("CurlingToolsManager");
	}
	
	public static EntityManagerFactory getEMF() {
		return emf;
	}

	public static void finalizePersistence(){
		emf.close();
	}

}
