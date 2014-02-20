package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.groupEditPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;

public class GroupEditPanel extends JPanel {

	private static final long serialVersionUID = 4161338262789665768L;

	private Group group;
	
	private JTextField nameGroup;
	private JTextField rankGroup;
	private JTextField nbTeamsGroup;
	private JTextField startTimeGroup;
	private JTextField endTimeGroup;
	
	public GroupEditPanel(final Group _group) {
		// GUI
		initializeGUI();
		// Update round
		setGroup(_group);
	}

	private void initializeGUI() {
		// Size
		setPreferredSize(new Dimension(300, 200));
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel rankLabel = new JLabel("Rank :");
		JLabel nbTeamsLabel = new JLabel("Nb teams :");
		JLabel timeStartLabel = new JLabel("Start time :");
		JLabel endStartLabel = new JLabel("End time :");
		// Values
		nameGroup = new JTextField("");
		rankGroup = new JTextField("");
		nbTeamsGroup = new JTextField("");
		startTimeGroup = new JTextField("");
		endTimeGroup = new JTextField("");
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
		// -- Nb teams
		gbc.gridy = 2;
		this.add(nbTeamsLabel, gbc);
		// -- Start time
		gbc.gridy = 3;
		this.add(timeStartLabel, gbc);
		// -- End time
		gbc.gridy = 4;
		this.add(endStartLabel, gbc);
		// Values column
		gbc.insets.left = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		// -- Name
		gbc.gridy = 0;
		this.add(nameGroup, gbc);
		// -- Rank
		gbc.gridy = 1;
		this.add(rankGroup, gbc);
		// -- Nb teams
		gbc.gridy = 2;
		this.add(nbTeamsGroup, gbc);
		// -- Start time
		gbc.gridy = 3;
		this.add(startTimeGroup, gbc);
		// -- End time
		gbc.gridy = 4;
		this.add(endTimeGroup, gbc);
	}

	private void setGroup(Group _group) {
		if(_group != null){
			group = _group;
		} else {
			group = new Group();
		}
		// Update GUI
		updateGUI();
	}

	private void updateGUI() {
		final String name;
		final String rank;
		final String nbteams;
		final String startTime;
		final String endTime;
		if(group != null){
			name = group.getName();
			rank = String.valueOf(group.getRank());
			nbteams = String.valueOf(group.getNbTeams());
			startTime = group.getVerboseStartTime();
			endTime = group.getVerboseEndTime();
		}else{
			name = "";
			rank = "";
			nbteams = "";
			startTime = "";
			endTime = "";
		}
		GUIUtils.invokeLaterInEDT(new Runnable() {
			@Override
			public void run() {
				nameGroup.setText(name);
				rankGroup.setText(rank);
				nbTeamsGroup.setText(nbteams);
				startTimeGroup.setText(startTime);
				endTimeGroup.setText(endTime);
			}
		});
	}
	
	public Group getGroupUpdated(){
		// Get values
		final String nameUpdated = nameGroup.getText();
		final int rankUpdated = Integer.valueOf(rankGroup.getText());
		final int nbTeamsUpdated = Integer.valueOf(nbTeamsGroup.getText());
		// Time
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date startTimeUpdated;
		try {
			startTimeUpdated = sdf.parse(startTimeGroup.getText().trim());
		} catch (ParseException e) {
			startTimeUpdated = new Date();
			e.printStackTrace();
		}
		Date endTimeUpdated;
		try {
			endTimeUpdated = sdf.parse(endTimeGroup.getText().trim());
		} catch (ParseException e) {
			endTimeUpdated = new Date();
			e.printStackTrace();
		}

		// Update round
		group.setName(nameUpdated);
		group.setRank(rankUpdated);
		group.setNbTeams(nbTeamsUpdated);
		group.setStartTime(startTimeUpdated);
		group.setEndTime(endTimeUpdated);

		// Return
		return group;
	}
	
}
