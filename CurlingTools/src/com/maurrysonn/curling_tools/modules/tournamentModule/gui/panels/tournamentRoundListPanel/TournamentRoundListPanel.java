package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.core.utils.GUIUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.TournamentRoundFormDialog;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel.TournamentRoundDetailPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentRoundListPanel extends JPanel {

	private static final long serialVersionUID = 509996266569105222L;

	private boolean editionMode;

	private List<Round> roundList;

	private JPanel titlePane;
	private JPanel roundListPane;
	private JPanel actionsPane;

	private JLabel titleLabel;

	private JButton addActionBtn;

	private List<JPanel> roundDetailPanelList;

	public TournamentRoundListPanel(final Collection<Round> _roundList, final boolean _editionMode) {
		roundList = new ArrayList<Round>();
		roundDetailPanelList = new ArrayList<JPanel>();
		initializeGUI();
		initializeListeners();
		setEditionMode(_editionMode);
		setRoundList(_roundList);
	}

	private void initializeListeners() {
		addActionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TournamentRoundFormDialog creationDialog = new TournamentRoundFormDialog(null, null);
				creationDialog.setVisible(true);
				// TODO AP Fire creation round
				if(creationDialog.getRound() != null) {
					fireCreationRound(creationDialog.getRound());
				}
			}
		});
	}

	public TournamentRoundListPanel(final Collection<Round> _roundList) {
		this(_roundList, false);
	}

	private void initializeGUI() {
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
		// Actions
		actionsPane = new JPanel();
		titlePane.add(actionsPane);

		addActionBtn = new JButton("Add");
		actionsPane.add(addActionBtn);		
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

	public void addRound(final Round _round) {
		// XXX amaury - Delete print
		System.out.println("TournamentRoundListPanel.addRound()");
		if(_round != null) {
			roundList.add(_round);
			updateGUI();
		}
	}

	public void resetRoundList() {
		setRoundList(null);
	}

	private void updateGUI() {
		// XXX amaury - Delete print
		System.out.println("TournamentRoundListPanel.updateGUI()");
		// Update GUI
		actionsPane.setVisible(isEditionMode());
		// Update Rounds details
		roundDetailPanelList.clear();
		roundListPane.removeAll();
		for(Round round : roundList) {
			addRoundPanel(round);
		}
		revalidate();
	}

	private void addRoundPanel(final Round round) {
		// XXX amaury - Delete print
		System.out.println("TournamentRoundListPanel.addRoundPanel() - " + round);
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

	public boolean isEditionMode() {
		return editionMode;
	}

	public void setEditionMode(boolean editionMode) {
		this.editionMode = editionMode;
		updateGUI();
	}

	/*
	 * EVENTS
	 */
	private TournamentRoundListListener[] getTournamentRoundListListeners() {
		return listenerList.getListeners(TournamentRoundListListener.class);
	}

	public void addTournamentRoundListListener(final TournamentRoundListListener l) {
		listenerList.add(TournamentRoundListListener.class, l);
	}

	public void removeTournamentRoundListListener(final TournamentRoundListListener l) {
		listenerList.remove(TournamentRoundListListener.class, l);
	}

	private void fireCreationRound(final Round _round) {
		// XXX amaury - Delete print
		System.out.println("TournamentRoundListPanel.fireCreationRound() - Round=" + _round);
		for (final TournamentRoundListListener l : getTournamentRoundListListeners()) {
			l.creationRound(_round);
		}
	}



}
