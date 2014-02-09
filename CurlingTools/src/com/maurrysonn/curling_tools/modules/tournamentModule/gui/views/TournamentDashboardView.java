package com.maurrysonn.curling_tools.modules.tournamentModule.gui.views;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.DashboardControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.DashboardModelListener;

public class TournamentDashboardView implements DashboardModelListener {

	// Controler
	DashboardControler controler;

	// Panel container
	// private JPanel container;

	public TournamentDashboardView(final DashboardControler _controler) {
		controler = _controler;
	}
	
	@Override
	public void dashboardNewTournamentSelected(Tournament _tournament) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dashboardTournamentReset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dashboardTournamentUpdated(Tournament _tournament) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dashboardTournamentRoundAdded(Round _round) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dashboardTournamentRoundUpdated(Round _round) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DashboardTournamentRoundRemoved(Round _round) {
		// TODO Auto-generated method stub
		
	}

}
