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

	private List<Round> roundList;
	
	private JPanel titlePane;
	private JPanel roundListPane;
	
	private JLabel titleLabel;
	private List<JPanel> roundDetailPanelList;
	
	public TournamentRoundListPanel(final Collection<Round> _roundList) {
		roundList = new ArrayList<Round>();
		roundDetailPanelList = new ArrayList<JPanel>();
		initGUI();
		setRoundList(_roundList);
	}

	private void initGUI() {
		// Main layout
		setLayout(new BorderLayout());
		// Title pane
		titlePane = new JPanel();
		add(titlePane, BorderLayout.PAGE_START);
		// Main pane
		roundListPane = new JPanel();
		roundListPane.setLayout(new BoxLayout(roundListPane, BoxLayout.PAGE_AXIS));
		add(roundListPane, BorderLayout.CENTER);
		// Title
		titleLabel = new JLabel("Rounds");
		titlePane.add(titleLabel);
	}
	
	public void setRoundList(final Collection<Round> _roundList) {
		roundList.clear();
		if(_roundList != null) {
			for (Round round : _roundList) {
				if(round != null) {
					roundList.add(round);
				}
			}			
		}
		updateGUI();
	}
	
	public void resetRoundList() {
		setRoundList(null);
	}
	
	private void updateGUI() {
		roundDetailPanelList.clear();
		roundListPane.removeAll();
		for(Round round : roundList) {
			addRoundPanel(round);
		}
	}

	private void addRoundPanel(final Round round) {	
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
