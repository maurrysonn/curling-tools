package com.maurrysonn.curling_tools.modules.tournamentModule.tests;

import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IRoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

public class RoundTest {

	/*
	 * Get tournament and add a new round.
	 */
	public static void main2(String[] args) {
		
		TournamentManager manager = new TournamentManager();
		ITournamentModel tournamentModel = manager.getTournamentModel();
		IRoundModel roundModel = manager.getRoundModel();

		// Get tournament
		Tournament soCurl = tournamentModel.get(1);
		// XXX AP - Delete print
		System.out.println("Tournament selected :");
		// XXX AP - Delete print
		System.out.println(soCurl);
		
		if(soCurl == null) return;

		// Create Round
		Round round1 = new Round();
		round1.setName("Round 1");
		round1.setRank(1);
		round1.setType(RoundType.GROUP);
		round1.setTournament(soCurl);
		// XXX AP - Delete print
		System.out.println("Round created : " + round1);

		// Add round to tournament
		soCurl.addRound(round1);

		// Rounds list
		// XXX AP - Delete print
		System.out.println("ROUND LIST :");
		for (final Round round : roundModel.list()) {
			// XXX AP - Delete print
			System.out.println(round);
		}
	}

	/*
	 * List rounds.
	 */
	public static void main(String[] args) {

		TournamentManager manager = new TournamentManager();
		IRoundModel roundModel = manager.getRoundModel();

		// XXX AP - Delete print
		System.out.println("LIST ROUNDS : ");
		for (final Round round : roundModel.list()) {
			// XXX AP - Delete print
			System.out.println(round + " => " + round.getTournament());
		}
	}
	
}
