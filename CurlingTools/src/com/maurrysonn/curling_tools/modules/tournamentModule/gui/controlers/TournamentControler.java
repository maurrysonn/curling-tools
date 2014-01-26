package com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers;

import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.views.TournamentHomeView;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;

public class TournamentControler {

	// Model
	private ITournamentModel tournamentModel;
	
	// Tournament Home View
	private TournamentHomeView homeView;
	
	public TournamentControler(final ITournamentModel _TournamentModel) {
		// Set model
		tournamentModel = _TournamentModel;
		// Create home view
		homeView = new TournamentHomeView(this);
		// Listeners
		tournamentModel.addTournamentModelListener(homeView);
	}

	public TournamentHomeView getTournamentHomeview() {
		return homeView;
	}
	
	public List<Tournament> getListTournament() {
		return tournamentModel.list();
	}

	public void updateTournament(Tournament tournament) {
		// TODO Tournament validation
		tournamentModel.update(tournament);
	}

	public void addTournament(Tournament tournament) {
		// TODO Tournament validation
		tournamentModel.add(tournament);
	}

	public void deleteTournament(Tournament tournament) {
		// TODO Tournament Validation
		tournamentModel.remove(tournament);		
	}
		
}
