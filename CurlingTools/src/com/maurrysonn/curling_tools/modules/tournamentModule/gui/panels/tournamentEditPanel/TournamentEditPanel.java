package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentEditPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class TournamentEditPanel extends JPanel {

	private static final long serialVersionUID = -8548466887716250637L;

	private Tournament tournament;
	
	private JTextField nameTournament;
	private JTextField startDateTournament;
	private JTextField endDateTournament;
	private JTextField rinkTournament;
	private JTextField clubTournament;

	public TournamentEditPanel(){
		this(null);
	}
	
	public TournamentEditPanel(final Tournament _tournament) {
		// IHM
		createIHM();
		// Update club
		setTournament(_tournament);
	}
	
	private void updateValues() {
		nameTournament.setText(tournament.getName());
		startDateTournament.setText(tournament.getVerboseStartDate());
		endDateTournament.setText(tournament.getVerboseEndDate());
		clubTournament.setText(tournament.getClub());
		rinkTournament.setText(tournament.getRink());
	}

	private void createIHM() {
		// Size
		setPreferredSize(new Dimension(300, 200));
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel startDateLabel = new JLabel("Start date :");
		JLabel endDateLabel = new JLabel("End date :");
		JLabel clubLabel = new JLabel("Club :");
		JLabel rinkLabel = new JLabel("Rink :");
		// Values
		nameTournament = new JTextField("");
		startDateTournament = new JTextField("");
		endDateTournament = new JTextField("");
		rinkTournament = new JTextField("");
		clubTournament = new JTextField("");
		// Layout
		this.setLayout(new GridBagLayout());
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

		// TODO - Remove Debug
		this.setBorder(BorderFactory.createLineBorder(Color.orange));
	}

	public void setTournament(final Tournament _tournament){
		if(_tournament != null){
			this.tournament = _tournament;
		} else {
			this.tournament = new Tournament();
		}
		// Update IHM
		updateValues();
	}
	
	public Tournament getTournamentUpdated(){
		// TODO AP - EDT
		// Get values
		final String nameUpdated = nameTournament.getText();
		final String clubUpdated = clubTournament.getText();
		final String rinkUpdated = rinkTournament.getText();
		final String startDateUpdatedStr = startDateTournament.getText();
		final String endDateUpdatedStr = endDateTournament.getText();
		// TODO AP - Tournament date display
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDateUpdated;
		try {
			startDateUpdated = sdf.parse(startDateUpdatedStr.trim());
		} catch (ParseException e) {
			startDateUpdated = new Date();
			// XXX AP - Delete print
			System.err.println("TournamentEditPanel.getTournamentUpdated() - Error during parsing date.");
			e.printStackTrace();
		}
		Date endDateUpdated;
		try {
			endDateUpdated = sdf.parse(endDateUpdatedStr.trim());
		} catch (ParseException e) {
			endDateUpdated = new Date();
			// XXX AP - Delete print
			System.err.println("TournamentEditPanel.getTournamentUpdated() - Error during parsing date.");
			e.printStackTrace();
		}
		// Update club
		tournament.setName(nameUpdated);
		tournament.setStartDate(startDateUpdated);
		tournament.setEndDate(endDateUpdated);
		tournament.setRink(rinkUpdated);
		tournament.setClub(clubUpdated);
		// Return
		return tournament;
	}

}
