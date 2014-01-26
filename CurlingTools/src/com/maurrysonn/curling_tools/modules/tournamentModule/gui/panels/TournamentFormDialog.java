package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentEditPanel.TournamentEditPanel;

public class TournamentFormDialog extends JDialog {

	private static final long serialVersionUID = -8845219300141001976L;

	private TournamentEditPanel contentPanel;
	private JButton actionBtn;
	private JButton cancelBtn;

	private boolean creationMode;

	private Tournament data;

	public TournamentFormDialog(final JPanel _parent) {
		this(_parent, null);
	}

	public TournamentFormDialog(final JPanel _parent, final Tournament _tournament) {
		// TODO Dialog parent
		super();
		setModal(true);
		creationMode = (_tournament == null);
		data = null;
		initGUI(_tournament);
		initListeners();
		pack();
	}

	private void initListeners() {
		actionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data = contentPanel.getTournamentUpdated();
				setVisible(false);
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				data = null;
				setVisible(false);
			}
		});
	}

	private void initGUI(final Tournament _tournament) {
		contentPanel = new TournamentEditPanel(_tournament);
		if (creationMode) {
			this.setTitle("Creation");
			actionBtn = new JButton("Create");
		} else {
			this.setTitle("Modification");
			actionBtn = new JButton("Save");
		}
		cancelBtn = new JButton("Cancel");
		// Layouts controls
		JPanel controlsPanel = new JPanel();
		controlsPanel.add(actionBtn);
		controlsPanel.add(cancelBtn);
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(controlsPanel, BorderLayout.PAGE_END);
	}

	public Tournament getTournament() {
		return data;
	}
}
