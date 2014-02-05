package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public class TournamentRoundDetailPanel extends JPanel {

	private static final long serialVersionUID = -5445973340047580681L;

	private Round model;
	
	private JLabel nameLabel;
	private JLabel typeLabel;
	
	public TournamentRoundDetailPanel(final Round round) {
		initGUI();
		setRound(round);
	}

	private void initGUI() {
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		// Main layout
		setLayout(new GridBagLayout());
				
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		/*
		 * Title row
		 */
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		// Name
		nameLabel = new JLabel();
		nameLabel.setBorder(BorderFactory.createLineBorder(Color.yellow));
		gbc.gridx = 0;
		gbc.weightx = 1;
		add(nameLabel, gbc);
		// Type
		typeLabel = new JLabel();
		typeLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		add(typeLabel, gbc);
		
		/*
		 * Groups
		 */
	}
	
	public void setRound(final Round _round) {
		model = _round;
		updateData();
	}
	
	
	private void updateData() {
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
