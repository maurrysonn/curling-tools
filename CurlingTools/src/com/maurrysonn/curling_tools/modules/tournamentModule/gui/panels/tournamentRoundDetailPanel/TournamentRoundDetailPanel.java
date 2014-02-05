package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.awt.BorderLayout;
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

	private JPanel titlePane;
	private JLabel nameLabel;
	private JLabel typeLabel;
	
	public TournamentRoundDetailPanel(final Round round) {
		initGUI();
		updateData(round);
	}

	private void initGUI() {
		setBorder(BorderFactory.createLineBorder(Color.blue));
		// Title Pane
		titlePane = new JPanel(new BorderLayout());
		titlePane.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;

		// Name
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		nameLabel = new JLabel();
		nameLabel.setOpaque(true);
		nameLabel.setBackground(Color.red);
		titlePane.add(nameLabel, gbc);
		
		// Type
		typeLabel = new JLabel();
		typeLabel.setOpaque(true);
		typeLabel.setBackground(Color.orange);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		titlePane.add(typeLabel, gbc);
		
		// Main Layout
		setLayout(new BorderLayout());
		add(titlePane, BorderLayout.PAGE_START);
	}
	
	private void updateData(final Round round) {
		final String name;
		final String type;
		if(round != null) {
			name = round.getName();
			type = round.getType().name();
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
