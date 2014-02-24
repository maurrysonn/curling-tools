package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundEditPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

public class TournamentRoundEditPanel extends JPanel {

	private static final long serialVersionUID = 4161338262789665768L;

	private Round round;
	
	private JTextField nameRound;

	private JTextField rankRound;

	private JComboBox<RoundType> typeRound;

	public TournamentRoundEditPanel(final Round _round) {
		// GUI
		initializeGUI();
		// Update round
		setRound(_round);
	}

	private void initializeGUI() {
		// Size
		setPreferredSize(new Dimension(300, 200));
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel rankLabel = new JLabel("Rank :");
		JLabel typeLabel = new JLabel("Type :");
		// Values
		nameRound = new JTextField("");
		rankRound = new JTextField("");
		typeRound = new JComboBox<RoundType>(RoundType.values());
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
		// -- Rank
		gbc.gridy = 1;
		this.add(rankLabel, gbc);
		// -- Type
		gbc.gridy = 2;
		this.add(typeLabel, gbc);
		// Values column
		gbc.insets.left = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		// -- Name
		gbc.gridy = 0;
		this.add(nameRound, gbc);
		// -- Rank
		gbc.gridy = 1;
		this.add(rankRound, gbc);
		// -- Type
		gbc.gridy = 2;
		this.add(typeRound, gbc);
	}

	private void setRound(Round _round) {
		if(_round != null){
			round = _round;
		} else {
			round = new Round();
		}
		// Update GUI
		updateGUI();
	}

	private void updateGUI() {
		final String name;
		final String rank;
		final RoundType type;
		if(round != null){
			name = round.getName();
			rank = String.valueOf(round.getRank());
			type = round.getType();
		}else{
			name = "";
			rank = "";
			type = RoundType.GROUP;
		}
		GUIUtils.invokeLaterInEDT(new Runnable() {
			@Override
			public void run() {
				nameRound.setText(name);
				rankRound.setText(rank);
				typeRound.setSelectedItem(type);
			}
		});
	}
	
	public Round getRoundUpdated(){
		// Get values
		final String nameUpdated = nameRound.getText();
		final int rankUpdated = Integer.valueOf(rankRound.getText());
		final RoundType type = (RoundType)typeRound.getSelectedItem();
		// Update round
		round.setName(nameUpdated);
		round.setRank(rankUpdated);
		round.setType(type);
		// Return
		return round;
	}
	
}
