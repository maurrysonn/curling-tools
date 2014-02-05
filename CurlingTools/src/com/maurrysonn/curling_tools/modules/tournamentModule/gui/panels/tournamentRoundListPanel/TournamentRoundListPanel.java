package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel.TournamentRoundDetailPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentRoundListPanel extends JPanel {

	private static final long serialVersionUID = 509996266569105222L;

	private JPanel titlePane;
	private JPanel roundListPane;
	
	private JLabel titleLabel;
	private List<JPanel> roundDetailPanelList;
	
	public TournamentRoundListPanel(final Collection<Round> roundsList) {
		roundDetailPanelList = new ArrayList<JPanel>();

		initGUI();
		initializeData(roundsList);
	}

	private void initGUI() {
		// Main layout
		setLayout(new BorderLayout());
		// Title pane
		titlePane = new JPanel();
		add(titlePane, BorderLayout.PAGE_START);
		// Main pane
		roundListPane = new JPanel();
		roundListPane.setBorder(BorderFactory.createLineBorder(Color.blue));
		roundListPane.setLayout(new BoxLayout(roundListPane, BoxLayout.PAGE_AXIS));
		add(roundListPane, BorderLayout.CENTER);
		// Title
		titleLabel = new JLabel("Rounds");
		titleLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		titlePane.add(titleLabel);
	}
	
	private void initializeData(final Collection<Round> roundsList) {
		for (Round round : roundsList) {
			if(round != null) addRound(round);
		}
	}
	
	private void addRound(final Round round) {	
		// Create round detail panel
		final TournamentRoundDetailPanel tournamentRoundDetailPanel = new TournamentRoundDetailPanel(round);
		roundDetailPanelList.add(tournamentRoundDetailPanel);
		// Add panel to layout
		roundListPane.add(tournamentRoundDetailPanel);
	}

	
	public static void main(String[] args) {

		// Get tournament
		TournamentModel tournamentModel = new TournamentModel();
		Tournament tournament = tournamentModel.get(1);
		if( tournament == null ) {
			// XXX amaury - Delete print
			System.out.println("===> TOURNAMENT NOT AVAILABLE : Stopping app.");
		}

		// Create RoundListPanel
		// XXX amaury - Delete print
		System.out.println(">>> List of rounds :");
		for (Round round : tournament.getRounds()) {
			// XXX amaury - Delete print
			System.out.println("-> " + round);
		}
		JPanel roundListPanel = new TournamentRoundListPanel(tournament.getRounds());
		
		// Get Frame
		JFrame frame = GUIUtils.createFrameForTest();
		// Add Main Panel
		frame.getContentPane().add(roundListPanel, BorderLayout.CENTER);
		// Show
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
