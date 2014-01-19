package com.maurrysonn.curling_tools.modules.tournamentModule;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentManager {

	private ITournamentModel model;
	
	public TournamentManager() {
		model = new TournamentModel();
	}

	public ITournamentModel getModel() {
		return model;
	}
	
	public static void main(String[] args) {
		
		TournamentManager manager = new TournamentManager();
		ITournamentModel model = manager.getModel();
		
		Tournament t1 = new Tournament();
		t1.setName("Tournament 2");
		t1.setClub("Belfort CC");
		t1.setRink("Patinoire de Belfort");
		t1.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 22).getTime());
		t1.setEndDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime());

		// model.add(t1);
		
		// XXX AP - Delete print
		System.out.println(" ==== LIST OF TOURNAMENT === ");
		List<Tournament> tournaments = model.list();
		for (final Tournament t : tournaments) {
			// XXX AP - Delete print
			System.out.println(t);
		}
	}
	
}
