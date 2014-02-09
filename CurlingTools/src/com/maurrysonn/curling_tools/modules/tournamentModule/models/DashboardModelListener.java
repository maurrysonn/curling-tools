package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface DashboardModelListener extends EventListener {

	// Tournament
	public void dashboardNewTournamentSelected(final Tournament _tournament);
	public void dashboardTournamentReset();
	public void dashboardTournamentUpdated(final Tournament _tournament);
	// Rounds
	public void dashboardTournamentRoundAdded(final Round _round);
	public void dashboardTournamentRoundUpdated(final Round _round);
	public void DashboardTournamentRoundRemoved(final Round _round);
}
