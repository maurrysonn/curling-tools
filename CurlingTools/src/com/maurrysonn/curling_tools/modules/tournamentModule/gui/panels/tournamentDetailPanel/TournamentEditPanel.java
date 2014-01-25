package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;
import com.maurrysonn.curling_tools.modules.clubModule.models.IClubModel;
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
		
		clubTournament.setText(tournament.getClub());
	}

	private void createIHM() {
		// Size
		this.setPreferredSize(new Dimension(300, 100));
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel shortNameLabel = new JLabel("Short Name :");
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
		this.add(nameTournament, gbc);
		gbc.gridy = 1;
		this.add(clubTournament, gbc);
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
		// Update club
		tournament.setName(nameUpdated);
		tournament.setStartDate(new Date());
		tournament.setEndDate(new Date());
		tournament.setRink("");
		tournament.setClub(clubUpdated);
		// Return
		return tournament;
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
//		frame.getContentPane().add(new TournamentEditPanel(club), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

	}
	
}
