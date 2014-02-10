package com.maurrysonn.curling_tools.modules.tournamentModule.gui.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.DashboardControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel.TournamentDetailPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel.TournamentRoundListPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.DashboardModelListener;

public class TournamentDashboardView extends AbstractView implements DashboardModelListener {

	// Controler
	DashboardControler controler;

	// Panels
	private TournamentDetailPanel detailPanel;
	private TournamentRoundListPanel roundListPanel;
	
	public TournamentDashboardView(final DashboardControler _controler) {
		super();
		controler = _controler;
		initializeComponents();
	}
	
	private void initializeComponents() {
		// Size of container
		container.setPreferredSize(new Dimension(600, 500));

		// Create panels
		detailPanel = new TournamentDetailPanel();
		roundListPanel = new TournamentRoundListPanel(null);

		// Layouts
		container.add(detailPanel, BorderLayout.LINE_START);
		container.add(roundListPanel, BorderLayout.CENTER);
	}

	@Override
	public void dashboardNewTournamentSelected(Tournament _tournament) {
		// Set the new tournament
		detailPanel.setTournament(_tournament);
		roundListPanel.setRoundList(_tournament.getRounds());
	}

	@Override
	public void dashboardTournamentReset() {
		// Reset panels
		detailPanel.resetTournament();
		roundListPanel.resetRoundList();
	}

	@Override
	public void dashboardTournamentUpdated(Tournament _tournament) {
		// Update the tournament
		detailPanel.setTournament(_tournament);		
		roundListPanel.setRoundList(_tournament.getRounds());
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

	@Override
	public void initializeData() {
		// TODO AP - To remove
		final TournamentManager manager = TournamentManager.getInstance();
		final Tournament t = manager.getTournamentModel().get(1);
		if(t != null) {
			// XXX AP - Delete print
			System.out.println(">>> Initializing Dashboard Model with : " + t);
			manager.getDashboardModel().setTournament(t);
		}
	}

}
