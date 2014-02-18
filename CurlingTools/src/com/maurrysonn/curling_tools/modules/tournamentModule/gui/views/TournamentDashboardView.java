package com.maurrysonn.curling_tools.modules.tournamentModule.gui.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.DashboardControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.TournamentRoundFormDialog;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel.TournamentDetailPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel.TournamentRoundListListener;
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
		initializeListeners();
	}
	
	private void initializeListeners() {
		roundListPanel.addTournamentRoundListListener(new TournamentRoundListListener() {
			@Override
			public void creationRoundPerformed(Round _round) {
				controler.addTournamentRound(_round);
			}

			@Override
			public void editionRoundPerformed(Round _round) {
				// TODO Auto-generated method stub
				// XXX amaury - Delete print
				System.out
						.println("TournamentDashboardView.{...}.editionRoundPerformed() - " + _round);
				// TODO Auto-generated method stub
				if (_round != null) {
					TournamentRoundFormDialog modificationDialog = new TournamentRoundFormDialog(container, _round);
					modificationDialog.setVisible(true);
					if(modificationDialog.getRound() != null){
						controler.updateRound(modificationDialog.getRound());
					}
				}


			}

			@Override
			public void deletionRoundPerformed(Round _round) {
				// TODO Auto-generated method stub
				// XXX amaury - Delete print
				System.out
				.println("TournamentDashboardView.{...}.deletionRoundPerformed() - " + _round);
				if(_round != null) {
					// Show Confirmation Msg
					final int result = JOptionPane.showConfirmDialog(container, "Are you sure to want delete this round ?", "Delete round",
							JOptionPane.YES_NO_OPTION);
					if (JOptionPane.YES_OPTION == result) {
						// XXX amaury - Delete print
						System.out.println("DELETE ROUND OK");
						controler.removeTournamantRound(_round);
					}
				}
			}
		});
		
	}

	private void initializeComponents() {
		// Size of container
		container.setPreferredSize(new Dimension(600, 500));

		// Create panels
		detailPanel = new TournamentDetailPanel();
		roundListPanel = new TournamentRoundListPanel(null, true);

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
		// XXX amaury - Delete print
		System.out
				.println("TournamentDashboardView.dashboardTournamentRoundAdded() - " + _round);
		roundListPanel.addRound(_round);
	}

	@Override
	public void dashboardTournamentRoundUpdated(Round _round) {
		// XXX amaury - Delete print
		System.out
				.println("TournamentDashboardView.dashboardTournamentRoundUpdated() - " + _round);
		roundListPanel.updateRound(_round);
	}

	@Override
	public void DashboardTournamentRoundRemoved(Round _round) {
		// XXX amaury - Delete print
		System.out.println("TournamentDashboardView.DashboardTournamentRoundRemoved()");
		roundListPanel.removeRound(_round);
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
