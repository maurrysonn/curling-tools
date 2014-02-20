package com.maurrysonn.curling_tools.modules.tournamentModule.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

public class TournamentRoundDBTest {

	public static Tournament createTournament(final String name, final String club,
			final String rink, final Date startDate,
			final Date endDate){
		Tournament tournament = new Tournament();
		tournament.setName(name);
		tournament.setClub(club);
		tournament.setRink(rink);
		tournament.setStartDate(startDate);
		tournament.setEndDate(endDate);
		return tournament;
	}

	public static Round createRound(final String _name, final int _rank,
			final RoundType _type, final Tournament _tournament) {
		Round round = new Round();
		round.setName(_name);
		round.setRank(_rank);
		round.setType(_type);
		// Add round to tournament
		_tournament.addRound(round);
		return round;
	}

	public static Group createGroup(final String name, final int rank, final Date startTime,
			final Date endTime, final Round round) {
		Group g = new Group();
		g.setName(name);
		g.setRank(rank);
		g.setStartTime(startTime);
		g.setEndTime(endTime);
		// Add group in round
		round.addGroup(g);
		return g;
	}
	
	public static void main(String[] args) {

		// TournamentManager manager = new TournamentManager();
		TournamentManager manager = TournamentManager.getInstance();
		ITournamentModel model = manager.getTournamentModel();
		
		// Create tournaments
		Tournament soCurl = createTournament("SoCurl 2014", "Besançon Curling Club",
				"Patinoire Lafayette", new GregorianCalendar(2014, Calendar.MAY, 23).getTime(),
				new GregorianCalendar(2014, Calendar.MAY, 25).getTime());
		model.add(soCurl);
		System.out.println("==> Tournament created : " + soCurl);

		// Create Rounds
		Round r1 = createRound("Round 1", 1, RoundType.GROUP, soCurl);
		System.out.println("==> Round created : " + r1);
		
		Group groupA1 = createGroup("Groupe A", 1, new GregorianCalendar(2014, Calendar.MAY, 23, 14, 0).getTime(),
				new GregorianCalendar(2014, Calendar.MAY, 23, 16, 0).getTime(), r1);
		// XXX amaury - Delete print
		System.out.println("==> Group created : " + groupA1);
		
		
//		Round r2 = createRound("Round 2", 2, RoundType.GROUP, soCurl);
//		System.out.println("==> Round created : " + r2);
//		Round r3 = createRound("Round 3", 3, RoundType.RANKING, soCurl);
//		System.out.println("==> Round created : " + r3);
//		Round r4 = createRound("Round 4", 4, RoundType.RANKING, soCurl);
//		System.out.println("==> Round created : " + r4);
//		Round r5 = createRound("Round 5", 5, RoundType.FINAL, soCurl);
//		System.out.println("==> Round created : " + r5);

		soCurl.printRounds();
		
		
		
		
		// Close persistence
		PersistenceUtils.finalizePersistence();
	}

}
