package com.maurrysonn.curling_tools.modules.clubModule.gui.views;

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
import com.maurrysonn.curling_tools.core.modules.ModelListener;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.panels.ClubFormDialog;
import com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubDetailPanel.ClubDetailPanel;
import com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubListPanel.ClubListPanel;
import com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubListPanel.ClubListPanelListener;

public class ClubHomeView implements ModelListener<Club> {

	// Controler
	ClubControler controler;

	// Panel container
	private JPanel container;

	// Views
	private ClubListPanel listClubPanel;
	private ClubDetailPanel detailClubPanel;

	// Controls
	private JButton newBtn;
	private JButton editBtn;
	private JButton deleteBtn;

	// Menu bar
	private static final String MENU_NAME = "Club";
	private JMenu menu;
	private JMenuItem newBtnMenu;
	private JMenuItem editBtnMenu;
	private JMenuItem deleteBtnMenu;

	// Controls text
	private static final String NEW = "New";
	private static final String EDIT = "Edit";
	private static final String DELETE = "Delete";

	public ClubHomeView(final ClubControler _controler) {
		this.controler = _controler;
		initializeComponents();
		initializeListeners();
	}

	private void initializeComponents() {
		// Create container
		container = new JPanel(new BorderLayout());
		container.setPreferredSize(new Dimension(500, 500));

		// Create panels
		listClubPanel = new ClubListPanel();
		detailClubPanel = new ClubDetailPanel();

		// Controls
		newBtn = GUIButtonFactory.createPrimaryButton(NEW);
		editBtn = GUIButtonFactory.createDefaultButton(EDIT);
		deleteBtn = GUIButtonFactory.createDefaultButton(DELETE);

		// Controls panel
		final JPanel controlsPanel = new JPanel();
		controlsPanel.add(this.newBtn);
		controlsPanel.add(this.editBtn);
		controlsPanel.add(this.deleteBtn);

		// Layouts
		container.add(controlsPanel, BorderLayout.PAGE_START);
		container.add(this.listClubPanel, BorderLayout.CENTER);
		container.add(this.detailClubPanel, BorderLayout.PAGE_END);

		// Menu
		menu = new JMenu(MENU_NAME);
		menu.setMnemonic(MENU_NAME.charAt(0));
		newBtnMenu = new JMenuItem(NEW);
		newBtnMenu.setMnemonic(NEW.charAt(0));
		editBtnMenu = new JMenuItem(EDIT);
		editBtnMenu.setMnemonic(EDIT.charAt(0));
		deleteBtnMenu = new JMenuItem(DELETE);
		deleteBtnMenu.setMnemonic(DELETE.charAt(0));
		menu.add(newBtnMenu);
		menu.add(editBtnMenu);
		menu.add(deleteBtnMenu);

		// TODO - Remove Debug
		container.setBorder(BorderFactory.createLineBorder(Color.red));
	}

	private void initializeListeners() {
		// List Club Panel notifications
		listClubPanel.addClubListViewListener(new ClubListPanelListener() {
			@Override
			public void clubSelectionReset() {
				detailClubPanel.resetClub();
			}

			@Override
			public void clubSelectionChanged(Club _club) {
				detailClubPanel.setClub(_club);
			}
		});
		// New Controls actions
		ActionListener newBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClubFormDialog creationDialog = new ClubFormDialog(container, null);
				creationDialog.setVisible(true);
				// XXX amaury - Delete print
				System.out.println("Dialog closed !");
				notifyCreationClub(creationDialog.getClub());
			}
		};
		newBtn.addActionListener(newBtnAl);
		newBtnMenu.addActionListener(newBtnAl);

		// Edit Controls actions
		ActionListener editBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get Current list selection
				final Club selected = listClubPanel.getSelectedClub();
				if (selected != null) {
					ClubFormDialog modificationDialog = new ClubFormDialog(container, selected);
					modificationDialog.setVisible(true);
					// XXX amaury - Delete print
					System.out.println("Dialog closed !");
					notifyModificationClub(modificationDialog.getClub());
				}
			}
		};
		editBtn.addActionListener(editBtnAl);
		editBtnMenu.addActionListener(editBtnAl);

		// Delete Controls actions
		ActionListener deleteBtnAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final Club selected = listClubPanel.getSelectedClub();
				if (selected != null) {
					// Show Confirmation Msg
					final int result = JOptionPane.showConfirmDialog(container, "Are you sure to want delete this club ?", "Delete club",
							JOptionPane.YES_NO_OPTION);
					if (JOptionPane.YES_OPTION == result) {
						notifyDeleteClub(selected);
					}
				}
			}
		};
		deleteBtn.addActionListener(deleteBtnAl);
		deleteBtnMenu.addActionListener(deleteBtnAl);
	}

	public void initializeData() {
		// Get data from model
		listClubPanel.initializeElements(controler.getListClub());
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

	private void notifyCreationClub(final Club club) {
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.notifyCreationClub() - Club=" + club);
		if (club != null) {
			controler.addClub(club);
		}
	}

	private void notifyModificationClub(final Club club) {
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.notifyModificationClub() - Club=" + club);
		if (club != null) {
			controler.updateClub(club);
		}
	}

	private void notifyDeleteClub(final Club club) {
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.notifyDeleteClub() - Club=" + club);
		if (club != null) {
			controler.deleteClub(club);
		}
	}

	// -------------------
	// Model notifications
	// -------------------

	@Override
	public void listChanged() {
		// Do nothing
	}

	@Override
	public void added(Club _club) {
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.clubAdded() - Club=" + _club);
		// Add the new club in list view
		this.listClubPanel.addElement((Club) _club);
	}

	@Override
	public void updated(Club _club) {
		// TODO Auto-generated method stub
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.clubUpdated() - Club=" + _club);
		this.listClubPanel.updateElement((Club) _club);
	}

	@Override
	public void removed(Club _club) {
		// XXX amaury - Delete print
		System.out.println("ClubHomeView.clubRemoved() - Club=" + _club);
		this.listClubPanel.removeElement((Club) _club);
	}
}
