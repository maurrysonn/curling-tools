package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface IDashboardModel {

	public Tournament getTournament();
	public void setTournament(final Tournament _tournament);
	
	public void addTournamentRound(final Round _round);
	public void updateTournamentRound(Round _round);
	public void removeTournamentRound(final Round _round);
	
	public void addDashboardModelListener(final DashboardModelListener l);
	public void removeDashboardModelListener(final DashboardModelListener l);
}
