package com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.views.TournamentDashboardView;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IDashboardModel;

public class DashboardControler {

	// Model
	private IDashboardModel dashboardModel;

	// Dashboard View
	private TournamentDashboardView dashboardView;


	public DashboardControler(final IDashboardModel _dashboardModel) {
		// Set model
		dashboardModel = _dashboardModel;
		// Create Dashboard View
		dashboardView = new TournamentDashboardView(this);
		// Listeners
		dashboardModel.addDashboardModelListener(dashboardView);
	}

	public TournamentDashboardView getDashboardView() {
		return dashboardView;
	}
	
	public void addTournamentRound(final Round _round) {
		dashboardModel.addTournamentRound(_round);
	}
	
	public void updateRound(Round _round) {
		dashboardModel.updateTournamentRound(_round);
	}
	
	public void removeTournamantRound(final Round _round) {
		dashboardModel.removeTournamentRound(_round);
	}

	public void addGroup(Group _group, Round _round) {
		dashboardModel.addGroup(_group, _round);
	}

}
