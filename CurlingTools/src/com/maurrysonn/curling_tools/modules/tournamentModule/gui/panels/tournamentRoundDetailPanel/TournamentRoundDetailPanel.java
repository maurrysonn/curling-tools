package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public class TournamentRoundDetailPanel extends JPanel {

	private static final long serialVersionUID = -5445973340047580681L;

	private Round model;
	
	// TODO AP - RoundListTableModel
	
	/*
	 * Title Round
	 */
	private JPanel titlePane;
	private JLabel nameLabel;
	private JLabel typeLabel;
	
	/*
	 * Groups Round
	 */
	private JTable roundsTable;
	
	public TournamentRoundDetailPanel(final Round round) {
		initGUI();
		setRound(round);
	}

	private void initGUI() {
		setBorder(BorderFactory.createLineBorder(Color.black));

		// Main layout
		setLayout(new BorderLayout());
		
		/*
		 * Title round
		 */
		
		// Title Pane
		titlePane = new JPanel(new GridBagLayout());
		add(titlePane, BorderLayout.PAGE_START);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;		
		gbc.gridy = 0;
		// Name
		nameLabel = new JLabel();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		titlePane.add(nameLabel, gbc);
		// Type
		typeLabel = new JLabel();
		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		titlePane.add(typeLabel, gbc);
		
		/*
		 * Groups
		 */
		initializeRoundsTable();
		add(roundsTable, BorderLayout.CENTER);
		
	}

	private void initializeRoundsTable() {
		// TODO AP - Use RoundListTableModel
		String[] columnNames = {"Groupe", "Time", "Statut"};
		Object[][] data = {
				{"Groupe A", "23/05/14 - 12:00 à 14:00", "Finished"},
				{"Groupe B", "23/05/14 - 14:00 à 16:00", "In progress"},
				{"Groupe C", "23/05/14 - 16:00 à 18:00", "Not Started"}
		};
		roundsTable = new JTable(data, columnNames);
	}
	
	public void setRound(final Round _round) {
		model = _round;
		updateData();
	}
	
	
	private void updateData() {
		// TODO - Updating RoundsListTableModel
		final String name;
		final String type;
		if(model != null) {
			name = model.getName();
			type = model.getType().name();
		} else {
			name = "";
			type = "";
		}
		GUIUtils.invokeLaterInEDT(new Runnable() {
			@Override
			public void run() {
				nameLabel.setText(name);
				typeLabel.setText(type);
			}
		});
	}
}
