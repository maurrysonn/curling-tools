package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentListPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class TournamentListPanel extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 2925212605952256857L;

	private TournamentTableModel model;
	private JTable listTournament;
	
	
	public TournamentListPanel() {
		this(null);
	}
	
	public TournamentListPanel(final List<Tournament> _tournamentListData) {
		// Initialize Model
		model = new TournamentTableModel();
		
		// Initialize GUI
		createGUI();
		
		// Initialize listeners
		initializeListener();

		// Initialize data
		if(_tournamentListData != null){
			initializeElements(_tournamentListData);
		}
	}


	private void createGUI() {
		this.setPreferredSize(new Dimension(300, 300));
		
		// Create thee view with the model
		listTournament = new JTable(model);
		// Headers
		listTournament.getTableHeader().setEnabled(true);
		// Selection Mode
		listTournament.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Uses the entire height of the container
		listTournament.setFillsViewportHeight(true);
		// Adding ScrollPane
		JScrollPane scrollpane = new JScrollPane(listTournament);
		
		// Layouts
		this.setLayout(new BorderLayout());
		this.add(scrollpane, BorderLayout.CENTER);
	}
	
	private void initializeListener() {
		listTournament.getSelectionModel().addListSelectionListener(this);
	}

	public Tournament getSelectedTournament(){
		if(listTournament.getSelectedRow() != -1){
			// Get the current selection
			final Tournament tournamentSelected = this.model.getItem(listTournament.getSelectedRow());
			return tournamentSelected;
		}
		return null;
	}

	// ----------------------------
	// Selection list notifications
	// ----------------------------
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// User's action finished
		if(!e.getValueIsAdjusting()){
			// Check if current selection
			if(listTournament.getSelectedRow() != -1){
				// Get the current selection
				final Tournament tournamentSelected = this.model.getItem(listTournament.getSelectedRow());
				// XXX amaury - Delete print
				System.out.println("Tournament Selected = " + tournamentSelected);
				fireTournamentSelectionChanged(tournamentSelected);
			}else{
				// XXX amaury - Delete print
				System.out.println("No current selection");
				fireTournamentSelectionReset();
			}
		}
	}
	
	public void initializeElements(final List<Tournament> _tournamentList){
		// clear current datas
		clearElements();
		// Add new data
		addElements(_tournamentList);
	}
	
	public void addElement(final Tournament _tournament){
		model.addElement(_tournament);
	}
	
	public void addElements(final List<Tournament> _tournamentList){
		model.addElements(_tournamentList);
	}
	
	public void clearElements(){
		model.clearElements();
	}
	
	public void updateElement(Tournament _tournament) {
		model.updateElement(_tournament);
	}

	public void removeElement(Tournament _tournament) {
		model.removeElement(_tournament);
	}
	
	// -----------------------
	// Listeners notifications
	// -----------------------
	
	private void fireTournamentSelectionChanged(final Tournament _tournamentSelected){
		for (final TournamentListPanelListener l : getTournamentListViewListener()) {
			l.tournamentSelectionChanged(_tournamentSelected);
		}
	}
	
	private void fireTournamentSelectionReset(){
		for (final TournamentListPanelListener l : getTournamentListViewListener()) {
			l.tournamentSelectionReset();
		}
	}

	public void addTournamentListViewListener(final TournamentListPanelListener _l){
		this.listenerList.add(TournamentListPanelListener.class, _l);
	}
	
	public void removeTournamentListViewListener(final TournamentListPanelListener _l){
		this.listenerList.remove(TournamentListPanelListener.class, _l);
	}
	
	private TournamentListPanelListener[] getTournamentListViewListener(){
		return this.listenerList.getListeners(TournamentListPanelListener.class);
	}

}
