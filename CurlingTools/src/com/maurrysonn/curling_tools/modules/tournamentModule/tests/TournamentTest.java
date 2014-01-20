package com.maurrysonn.curling_tools.modules.tournamentModule.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;

public class TournamentTest {

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
	
	public static void printTournaments(final List<Tournament> tournaments){
		// XXX AP - Delete print
		System.out.println(" ==== LIST OF TOURNAMENT === ");
		for (final Tournament t : tournaments) {
			// XXX AP - Delete print
			System.out.println(t);
		}
		// XXX AP - Delete print
		System.out.println(" =========================== ");
	}
	
	public static void main(String[] args) {
		
		TournamentManager manager = new TournamentManager();
		ITournamentModel model = manager.getTournamentModel();
		
		// XXX AP - Delete print
		System.out.println("==> BEFORE CREATION");
		printTournaments(model.list());
		
		// Create tournaments
		Tournament t1 = createTournament("SoCurl 2013", "Besançon Curling Club",
				"Patinoire Lafayette", new GregorianCalendar(2014, Calendar.FEBRUARY, 22).getTime(),
				new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime());
		Tournament t2 = createTournament("Franch championship", "FFSG",
				"Halle de Tivoli", new GregorianCalendar(2014, Calendar.MARCH, 13).getTime(),
				new GregorianCalendar(2014, Calendar.MARCH, 16).getTime());

		// Add tournaments
		model.add(t1);
		model.add(t2);

		// XXX AP - Delete print
		System.out.println("==> AFTER CREATION");
		printTournaments(model.list());
	}	
}
