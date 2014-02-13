package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	 * Flag edition mode
	 */
	private boolean editionMode;

	/*
	 * Title Round
	 */
	private JPanel titlePane;
	private JLabel nameLabel;
	private JLabel typeLabel;

	/*
	 * Actions
	 */
	private JPanel actionsPane;
	private JButton editActionBtn;
	private JButton deleteActionBtn;

	/*
	 * Groups Round
	 */
	private JTable roundsTable;

	public TournamentRoundDetailPanel(final Round round) {
		this(round, false);
	}

	public TournamentRoundDetailPanel(final Round round, final boolean _editionMode) {
		initGUI();
		initListeners();
		setEditionMode(_editionMode);
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
		 * Actions
		 */
		actionsPane = new JPanel();
		gbc.gridx = 2;
		titlePane.add(actionsPane,gbc);
		editActionBtn = new JButton("E");
		editActionBtn.setToolTipText("Edit");
		actionsPane.add(editActionBtn);
		deleteActionBtn = new JButton("R");
		deleteActionBtn.setToolTipText("Remove");
		actionsPane.add(deleteActionBtn);

		/*
		 * Groups
		 */
		initializeRoundsTable();
		add(roundsTable, BorderLayout.CENTER);	
	}

	private void initListeners() {
		// TODO Auto-generated method stub
		editActionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireEditActionPerformed(model);
			}
		});

		deleteActionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireDeleteActionPerformed(model);
			}
		});
	}

	private void initializeRoundsTable() {
		// TODO AP - Use RoundListTableModel
		String[] columnNames = {"Groupe", "Time", "Statut", "Actions"};
		Object[][] data = {
				{"Groupe A", "23/05/14 - 12:00 à 14:00", "Finished", new JButton(">>")},
				{"Groupe B", "23/05/14 - 14:00 à 16:00", "In progress", new JButton(">>")},
				{"Groupe C", "23/05/14 - 16:00 à 18:00", "Not Started", new JButton(">>")}
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

	public boolean isEditionMode() {
		return editionMode;
	}

	public void setEditionMode(boolean editionMode) {
		this.editionMode = editionMode;
		updateEditionGUI();
	}

	private void updateEditionGUI() {
		GUIUtils.invokeLaterInEDT(new Runnable() {
			@Override
			public void run() {
				actionsPane.setVisible(isEditionMode());
				revalidate();
			}
		});
	}


	/*
	 * EVENTS
	 */

	public void addTournamentRoundDetailListener(final TournamentRoundDetailPanelListener l) {
		listenerList.add(TournamentRoundDetailPanelListener.class, l);
	}
	
	public void removeTournamentRoundDetailListener(final TournamentRoundDetailPanelListener l) {
		listenerList.remove(TournamentRoundDetailPanelListener.class, l);
	}

	private void fireEditActionPerformed(final Round _round) {
		for (final TournamentRoundDetailPanelListener l : listenerList.getListeners(TournamentRoundDetailPanelListener.class)) {
			l.editActionPerformed(_round);
		}
	}

	private void fireDeleteActionPerformed(final Round _round) {
		for (final TournamentRoundDetailPanelListener l : listenerList.getListeners(TournamentRoundDetailPanelListener.class)) {
			l.deleteActionPerformed(_round);
		}
	}
}
