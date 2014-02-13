package com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers;

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
	
}
