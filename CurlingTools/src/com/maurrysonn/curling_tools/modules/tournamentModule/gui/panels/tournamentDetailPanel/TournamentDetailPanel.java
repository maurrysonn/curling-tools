package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class TournamentDetailPanel extends JPanel {

	private static final long serialVersionUID = 5869075635006515067L;
	
	private Tournament tournament;
	
	private JLabel nameTournament;
	private JLabel startDateTournament;
	private JLabel endDateTournament;
	private JLabel rinkTournament;
	private JLabel clubTournament;

	public TournamentDetailPanel() {
		// Initialize item
		tournament = null;
		// IHM
		createIHM();
	}

	private void createIHM() {
		setPreferredSize(new Dimension(300, 150));
		
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel startDateLabel = new JLabel("Start date :");
		JLabel endDateLabel = new JLabel("End date :");
		JLabel clubLabel = new JLabel("Club :");
		JLabel rinkLabel = new JLabel("Rink :");
		
		// Values
		nameTournament = new JLabel("");
		startDateTournament = new JLabel("");
		endDateTournament = new JLabel("");
		clubTournament = new JLabel("");
		rinkTournament = new JLabel("");
		
		// Layout
		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		// Global Params
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		
		// Labels column
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		// -- Name
		gbc.gridy = 0;
		this.add(nameLabel, gbc);
		// -- Start date
		gbc.gridy = 1;
		this.add(startDateLabel, gbc);
		// -- End date
		gbc.gridy = 2;
		this.add(endDateLabel, gbc);
		// -- Club
		gbc.gridy = 3;
		this.add(clubLabel, gbc);
		// -- Rink
		gbc.gridy = 4;
		this.add(rinkLabel, gbc);
		
		// Values column
		gbc.insets.left = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		// -- Name
		gbc.gridy = 0;
		this.add(nameTournament, gbc);
		// -- Start date
		gbc.gridy = 1;
		this.add(startDateTournament, gbc);
		// -- End date
		gbc.gridy = 2;
		this.add(endDateTournament, gbc);
		// -- Club
		gbc.gridy = 3;
		this.add(clubTournament, gbc);
		// -- Rink
		gbc.gridy = 4;
		this.add(rinkTournament, gbc);
	}

	public Tournament getTournament() {
		return tournament;
	}
	
	public boolean isTournamentDisplayed(){
		return (tournament != null);
	}

	public void setTournament(Tournament _tournament) {
		// Update model
		tournament = _tournament;
		// Update view
		updateView();
		// Notifications
		if(_tournament != null){
			fireTournamentChanged(_tournament);
		}else{
			fireTournamentReset();
		}
	}

	public void resetTournament(){
		setTournament(null);
	}
	
	private void updateView(){
		final String name;
		final String startDate;
		final String endDate;
		final String club;
		final String rink;
		if(tournament != null){
			name = tournament.getName();
			startDate = tournament.getVerboseStartDate();
			endDate = tournament.getVerboseEndDate();
			club = tournament.getClub();
			rink = tournament.getRink();
		}else{
			name = "";
			startDate = "";
			endDate = "";
			club = "";
			rink = "";
		}
		GUIUtils.invokeLaterInEDT(new Runnable() {
			@Override
			public void run() {
				GUIUtils.printThreadInfos();
				nameTournament.setText(name);
				startDateTournament.setText(startDate);
				endDateTournament.setText(endDate);
				clubTournament.setText(club);
				rinkTournament.setText(rink);
			}
		});
	}
	
	public void addTournamentDetailViewListener(final TournamentDetailPanelListener _l){
		listenerList.add(TournamentDetailPanelListener.class, _l);
	}
	
	public void removeTournamentDetailViewListener(final TournamentDetailPanelListener _l){
		this.listenerList.remove(TournamentDetailPanelListener.class, _l);
	}
	
	private TournamentDetailPanelListener[] getTournamentDetailListeners(){
		return listenerList.getListeners(TournamentDetailPanelListener.class);
	}
	
	private void fireTournamentChanged(final Tournament _tournament){
		for (final TournamentDetailPanelListener l : getTournamentDetailListeners()) {
			l.tournamentChanged(_tournament);
		}
	}

	private void fireTournamentReset(){
		for (final TournamentDetailPanelListener l : getTournamentDetailListeners()) {
			l.tournamentReset();
		}
	}
	
}
