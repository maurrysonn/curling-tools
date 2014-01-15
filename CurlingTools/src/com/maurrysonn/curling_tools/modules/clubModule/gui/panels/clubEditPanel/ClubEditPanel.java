package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubEditPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;
import com.maurrysonn.curling_tools.modules.clubModule.models.IClubModel;

public class ClubEditPanel extends JPanel {

	private static final long serialVersionUID = -8548466887716250637L;

	private Club club;
	
	private JTextField nameClub;
	private JTextField shortNameClub;

	public ClubEditPanel(){
		this(null);
	}
	
	public ClubEditPanel(final Club _club) {
		// IHM
		createIHM();
		// Update club
		setClub(_club);
	}
	
	private void updateValues() {
		this.nameClub.setText(this.club.getName());
		this.shortNameClub.setText(this.club.getShortName());
	}

	private void createIHM() {
		// Size
		this.setPreferredSize(new Dimension(300, 100));
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel shortNameLabel = new JLabel("Short Name :");
		// Values
		nameClub = new JTextField("");
		shortNameClub = new JTextField("");
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
		gbc.gridy = 0;
		this.add(nameLabel, gbc);
		gbc.gridy = 1;
		this.add(shortNameLabel, gbc);
		// Values column
		gbc.insets.left = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(nameClub, gbc);
		gbc.gridy = 1;
		this.add(shortNameClub, gbc);
		// TODO - Remove Debug
		this.setBorder(BorderFactory.createLineBorder(Color.orange));
	}

	public void setClub(final Club _club){
		if(_club != null){
			this.club = _club;
		} else {
			this.club = new Club();
		}
		// Update IHM
		updateValues();
	}
	
	public Club getClubUpdated(){
		// TODO AP - EDT
		// Get values
		final String nameUpdated = this.nameClub.getText();
		final String shortNameString = this.shortNameClub.getText();
		// Update club
		this.club.setName(nameUpdated);
		this.club.setShortName(shortNameString);
		// Return
		return this.club;
	}
	
	public static void main(String[] args) {
		// Model / Manager
		final IClubModel manager = new ClubModel();

		// Club
		final Club club = manager.get(1);
		
		// Frame
		JFrame frame = new JFrame();
		frame.setTitle("ClubFormTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new ClubEditPanel(club), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

	}
	
}
