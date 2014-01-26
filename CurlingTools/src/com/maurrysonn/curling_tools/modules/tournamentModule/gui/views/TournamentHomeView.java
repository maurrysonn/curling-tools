package com.maurrysonn.curling_tools.modules.tournamentModule.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.core.gui.GUIButtonFactory;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.TournamentControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.TournamentFormDialog;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel.TournamentDetailPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentListPanel.TournamentListPanel;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentListPanel.TournamentListPanelListener;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModelListener;

public class TournamentHomeView implements TournamentModelListener {

	// Controler
	TournamentControler controler;

	// Panel container
	private JPanel container;

	// Views
	private TournamentListPanel listTournamentPanel;
	private TournamentDetailPanel detailTournamentPanel;

	// Controls
	private JButton newBtn;
	private JButton editBtn;
	private JButton deleteBtn;

	// Menu bar
	private static final String MENU_NAME = "Tournament";
	private JMenu menu;
	private JMenuItem newBtnMenuItem;
	private JMenuItem editBtnMenuItem;
	private JMenuItem deleteBtnMenuItem;

	// Controls text
	private static final String NEW = "New";
	private static final String EDIT = "Edit";
	private static final String DELETE = "Delete";

	public TournamentHomeView(final TournamentControler _controler) {
		controler = _controler;
		initializeComponents();
		initializeListeners();
	}

	private void initializeComponents() {
		// Create container
		container = new JPanel(new BorderLayout());
		container.setPreferredSize(new Dimension(600, 500));

		// Create panels
		listTournamentPanel = new TournamentListPanel();
		detailTournamentPanel = new TournamentDetailPanel();

		// Controls
		newBtn = GUIButtonFactory.createPrimaryButton(NEW);
		editBtn = GUIButtonFactory.createDefaultButton(EDIT);
		deleteBtn = GUIButtonFactory.createDefaultButton(DELETE);

		// Controls panel
		final JPanel controlsPanel = new JPanel();
		controlsPanel.add(newBtn);
		controlsPanel.add(editBtn);
		controlsPanel.add(deleteBtn);

		// Layouts
		container.add(controlsPanel, BorderLayout.PAGE_START);
		container.add(listTournamentPanel, BorderLayout.CENTER);
		container.add(detailTournamentPanel, BorderLayout.PAGE_END);

		// Menu
		menu = new JMenu(MENU_NAME);
		menu.setMnemonic(MENU_NAME.charAt(0));
		newBtnMenuItem = new JMenuItem(NEW);
		newBtnMenuItem.setMnemonic(NEW.charAt(0));
		editBtnMenuItem = new JMenuItem(EDIT);
		editBtnMenuItem.setMnemonic(EDIT.charAt(0));
		deleteBtnMenuItem = new JMenuItem(DELETE);
		deleteBtnMenuItem.setMnemonic(DELETE.charAt(0));
		menu.add(newBtnMenuItem);
		menu.add(editBtnMenuItem);
		menu.add(deleteBtnMenuItem);

		// TODO - Remove Debug
		container.setBorder(BorderFactory.createLineBorder(Color.red));
	}

	private void initializeListeners() {
		// List Club Panel notifications
		listTournamentPanel.addTournamentListViewListener(new TournamentListPanelListener() {
			@Override
			public void tournamentSelectionReset() {
				detailTournamentPanel.resetTournametn();
			}
			
			@Override
			public void tournamentSelectionChanged(Tournament _tournament) {
				// TODO Auto-generated method stub
				detailTournamentPanel.setTournament(_tournament);
			}
		});
		
		// New Controls actions
		ActionListener newBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TournamentFormDialog creationDialog = new TournamentFormDialog(container, null);
				creationDialog.setVisible(true);
				// XXX amaury - Delete print
				System.out.println("Dialog closed !");
				notifyCreationTournament(creationDialog.getTournament());
			}
		};
		newBtn.addActionListener(newBtnAl);
		newBtnMenuItem.addActionListener(newBtnAl);

		// Edit Controls actions
		ActionListener editBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get Current list selection
				final Tournament selected = listTournamentPanel.getSelectedTournament();
				if (selected != null) {
					TournamentFormDialog modificationDialog = new TournamentFormDialog(container, selected);	
					modificationDialog.setVisible(true);
					// XXX amaury - Delete print
					System.out.println("Dialog closed !");
					notifyModificationTournament(modificationDialog.getTournament());
				}
			}
		};
		editBtn.addActionListener(editBtnAl);
		editBtnMenuItem.addActionListener(editBtnAl);

		// Delete Controls actions
		ActionListener deleteBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final Tournament selected = listTournamentPanel.getSelectedTournament();
				if (selected != null) {
					// Show Confirmation Msg
					final int result = JOptionPane.showConfirmDialog(container, "Are you sure to want delete this tournament ?", "Delete tournament",
							JOptionPane.YES_NO_OPTION);
					if (JOptionPane.YES_OPTION == result) {
						notifyDeleteClub(selected);
					}
				}
			}
		};
		deleteBtn.addActionListener(deleteBtnAl);
		deleteBtnMenuItem.addActionListener(deleteBtnAl);
	}

	public void initializeData() {
		// Get data from model
		listTournamentPanel.initializeElements(controler.getListTournament());
	}

	public JPanel getContainer() {
		return container;
	}

	public JMenu getMenu() {
		return menu;
	}

	// -----------------------
	// Controler notifications
	// -----------------------

	private void notifyCreationTournament(final Tournament tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.notifyCreationClub() - Tournament=" + tournament);
		if (tournament != null) {
			controler.addTournament(tournament);
		}
	}

	private void notifyModificationTournament(final Tournament tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.notifyModificationClub() - Tournament=" + tournament);
		if (tournament != null) {
			controler.updateTournament(tournament);
		}
	}

	private void notifyDeleteClub(final Tournament tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.notifyDeleteClub() - Tournament=" + tournament);
		if (tournament != null) {
			controler.deleteTournament(tournament);
		}
	}

	// -------------------
	// Model notifications
	// -------------------

	@Override
	public void tournamentListChanged() {
		// Do nothing		
	}

	@Override
	public void tournamentAdded(Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.tournamentAdded() - Tournament=" + _tournament);
		// Add the new club in list view
		listTournamentPanel.addElement(_tournament);
	}

	@Override
	public void tournamentUpdated(Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.tournamentUpdated() - Tournament=" + _tournament);
		listTournamentPanel.updateElement(_tournament);
	}

	@Override
	public void tournamentRemoved(Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentHomeView.tournamentRemoved() - Tournament=" + _tournament);
		listTournamentPanel.removeElement(_tournament);
	}

}
