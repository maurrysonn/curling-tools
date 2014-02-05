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
		// Creation of simple thread wich will sleep during 1 sec
		// for closing connections properly.
		try {
			System.out.println("===> Waiting for closing connections...");
			Thread.sleep(1000);
			System.out.println("===> Finished.");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					// XXX amaury - Delete print
//					System.out.println("===> Sleeping...");
//					Thread.sleep(1000);
//					// XXX amaury - Delete print
//					System.out.println("===> Sleeping finished.");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		t.start();
	}

}
