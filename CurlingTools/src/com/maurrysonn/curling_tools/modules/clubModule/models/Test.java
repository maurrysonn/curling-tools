package com.maurrysonn.curling_tools.modules.clubModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.playerModule.entities.Player;

public class Test {

	private IClubModel clubDataService; 
	
	public Test() {
		this.clubDataService = new ClubModel();
		this.clubDataService.addClubModelListener(new ClubModelListener() {
			@Override
			public void clubUpdated(final Club _club) {
				System.out
						.println("Test.Test().new ClubDataServiceListener() {...}.clubUpdated() - "+_club);
			}
			@Override
			public void clubRemoved(final Club _club) {
				System.out
						.println("Test.Test().new ClubDataServiceListener() {...}.clubRemoved() - "+_club);
			}
			@Override
			public void clubListChanged() {
				System.out
						.println("Test.Test().new ClubDataServiceListener() {...}.clubListChanged()");
			}			
			@Override
			public void clubAdded(final Club _club) {
				System.out
						.println("Test.Test().new ClubDataServiceListener() {...}.clubAdded() - " + _club);
			}
		});
	}
	
	private void listClubs(){
		List<Club> listClub = clubDataService.list();
		// Display clubs
		System.out.println("============================================");
		System.out.println("LIST OF CLUBS :");
		System.out.println("============================================");
		for(Club c : listClub){
			System.out.println(c);
		}
	}

	private void addClub(final String name, final String shortName){
		// New club
		Club newClub = new Club();
		newClub.setName(name);
		newClub.setShortName(shortName);
		// Save new club
		this.clubDataService.add(newClub);
	}

	public void initilizeData(){
		// Add new clubs
		System.out.println("Adding some clubs...");
		this.addClub("Besancon Curling Club", "BCC");
		this.addClub("Chamonix Curling Club", "CCC");
		this.addClub("Belfort Curling Club", "");
		this.addClub("Strasboug Curling Club", "");
		this.addClub("Premanon Curling Club", "");
		this.addClub("Viry Curling Club", "");
		System.out.println("Adding some clubs... OK");
	}
	
	public void finalize(){
		System.out.println("==== Finalize connection ====");
		PersistenceUtils.finalizePersistence();
	}
	
	
	public static void main(String[] args) {
		
		Test test = new Test();
		
		System.out.println("==== Starting Main ====");
		
		// List of clubs
		test.listClubs();
		
		// Initilization of datas
//		test.initilizeData();
		
		// List of clubs
		test.listClubs();
		
		// Finalize connection
		test.finalize();
		
	}
	
	public static void main2(String[] args) {

		Test test = new Test();

		// Display current clubs and players
		test.listClubs();
		System.out.println("============================================");
		System.out.println("LIST OF PLAYERS :");
		System.out.println("============================================");
		test.listPlayers();
		// Add items
		System.out.println("============================================");
		System.out.println("ADDING CLUBS :");
		System.out.println("============================================");
		test.addClub("Besancon Curling Club", "BCC");
		test.addClub("Chamonix Curling Club", "CCC");
		System.out.println("============================================");
		System.out.println("ADDING PLAYERS :");
		System.out.println("============================================");
		test.addPlayer("Pernette", "Amaury", 1);
		test.addPlayer("Humbert", "Pauline", 2);
		// Display new clubs
		System.out.println("============================================");
		System.out.println("LIST OF CLUBS :");
		System.out.println("============================================");
		test.listClubs();
		System.out.println("============================================");
		System.out.println("LIST OF PLAYERS :");
		System.out.println("============================================");
		test.listPlayers();
	}

	private void addPlayer(final String name, final String firstName, final long clubId){
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		// Start Transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Get club
		Club club = em.find(Club.class, clubId);
		// Create new player
		Player p = new Player();
		p.setName(name);
		p.setFirstName(firstName);
		p.setClub(club);
		// Save player
		em.persist(p);
		// Stop Transaction
		tx.commit();
		// Close EntityManager
		em.close();
	}


	private void listPlayers(){
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		// Query
		TypedQuery<Player> tQuery = em.createQuery("from Player", Player.class);
		// Query query = em.createQuery(queryString.toString());
		// Execute query
		List<Player> results = tQuery.getResultList() ;
		// Display players
		for(Player p : results){
			System.out.println(p);
		}
		// Close EntityManager
		em.close();
	}


}
