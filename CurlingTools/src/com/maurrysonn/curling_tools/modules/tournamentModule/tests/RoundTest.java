package com.maurrysonn.curling_tools.modules.tournamentModule.tests;

import java.util.List;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IRoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

public class RoundTest {

	public static void printRoundsList(final List<Round> rounds){
		// XXX AP - Delete print
		System.out.println("ROUND LIST :");
		for (final Round round : rounds) {
			// XXX AP - Delete print
			System.out.println(round);
		}
	}
	
	/*
	 * Get tournament and add a new round.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		TournamentManager manager = TournamentManager.getInstance();
		ITournamentModel tournamentModel = manager.getTournamentModel();
		IRoundModel roundModel = manager.getRoundModel();

		// Get tournament
		Tournament soCurl = tournamentModel.get(1);
		// XXX AP - Delete print
		System.out.println("Tournament selected :");
		// XXX AP - Delete print
		System.out.println(soCurl);
		
		if(soCurl == null) return;

		// XXX AP - Delete print
		System.out.println("== SoCurl Rounds :");
		soCurl.printRounds();
		
		// Create Round
		Round round = new Round();
		round.setName("Round 1");
		round.setRank(1);
		// round.setName("Round 2");
		// round.setRank(2);
		round.setType(RoundType.GROUP);
		// Add round to tournament
		soCurl.addRound(round);
		
		// XXX AP - Delete print
		System.out.println("Round created : " + round);

		// XXX AP - Delete print
		System.out.println("== SoCurl Rounds :");
		soCurl.printRounds();

		// XXX AP - Delete print
		System.out.println("== Global Rounds :");
		printRoundsList(roundModel.list());
		
		PersistenceUtils.finalizePersistence();
	}

	/*
	 * List rounds.
	 */
	public static void main1(String[] args) {

		TournamentManager manager = TournamentManager.getInstance();
		IRoundModel roundModel = manager.getRoundModel();
		ITournamentModel tournamentModel = manager.getTournamentModel();

		// XXX AP - Delete print
		System.out.println("===> LIST ROUNDS : ");
		for (final Round round : roundModel.list()) {
			// XXX AP - Delete print
			System.out.println(" -- " + round + " => " + round.getTournament());
		}
		// XXX amaury - Delete print
		System.out.println("===> END LIST ROUND");
		
		// Get tournament
		Tournament soCurl = tournamentModel.get(1);
		
		// Create Round
		Round round = new Round();
		round.setName("Round 1");
		round.setRank(1);
		// round.setName("Round 2");
		// round.setRank(2);
		round.setType(RoundType.GROUP);
		round.setTournament(soCurl);
		
		roundModel.add(round);
		
		
		// XXX AP - Delete print
		System.out.println("===> LIST ROUNDS : ");
		for (final Round round1 : roundModel.list()) {
			// XXX AP - Delete print
			System.out.println(" -- " + round1 + " => " + round1.getTournament());
		}
		// XXX amaury - Delete print
		System.out.println("===> END LIST ROUND");

		
		
		PersistenceUtils.finalizePersistence();
		
		
	}
	
}
