package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubListPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;

public class ClubListPanel extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 3588008022954107628L;

	private ClubTableModel model;
	private JTable listClub;
	
	public ClubListPanel(){
		this(null);
	}
	
	public ClubListPanel(final List<Club> _clubListData) {
		// Initialize Model
		model = new ClubTableModel();
		
		// Initialize GUI
		createGUI();
		
		// Initialize listeners
		initializeListener();

		// Initialize data
		if(_clubListData != null){
			initializeElements(_clubListData);
		}
	}
	
	private void createGUI(){
		this.setPreferredSize(new Dimension(300, 300));
		
		// Create thee view with the model
		listClub = new JTable(model);
		// Headers
		listClub.getTableHeader().setEnabled(true);
		// Selection Mode
		listClub.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Uses the entire height of the container
		listClub.setFillsViewportHeight(true);
		// Adding ScrollPane
		JScrollPane scrollpane = new JScrollPane(this.listClub);
		
		// Layouts
		this.setLayout(new BorderLayout());
		this.add(scrollpane, BorderLayout.CENTER);
		// TODO - Remove Debug
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
	}
	
	private void initializeListener(){
		listClub.getSelectionModel().addListSelectionListener(this);
	}
	
	// ----------------------------
	// Selection list notifications
	// ----------------------------
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// User's action finished
		if(!e.getValueIsAdjusting()){
			// Check if current selection
			if(this.listClub.getSelectedRow() != -1){
				// Get the current selection
				final Club clubSelected = this.model.getItem(this.listClub.getSelectedRow());
				// XXX amaury - Delete print
				System.out.println("Club Selected = " + clubSelected);
				this.fireClubSelectionChanged(clubSelected);
			}else{
				// XXX amaury - Delete print
				System.out.println("No current selection");
				this.fireClubSelectionReset();
			}
		}
	}
	
	public void initializeElements(final List<Club> _clubList){
		// clear current datas
		clearElements();
		// Add new data
		addElements(_clubList);
	}
	
	public void addElement(final Club _club){
		model.addElement(_club);
	}
	
	public void addElements(final List<Club> _clubList){
		model.addElements(_clubList);
	}
	
	public void clearElements(){
		model.clearElements();
	}
	
	public void updateElement(Club _club) {
		// TODO Auto-generated method stub
		model.updateElement(_club);
	}

	public void removeElement(Club _club) {
		model.removeElement(_club);
	}
	
	// -----------------------
	// Listeners notifications
	// -----------------------
	
	private void fireClubSelectionChanged(final Club _clubSelected){
		for (final ClubListPanelListener l : getClubListViewListener()) {
			l.clubSelectionChanged(_clubSelected);
		}
	}
	
	private void fireClubSelectionReset(){
		for (final ClubListPanelListener l : getClubListViewListener()) {
			l.clubSelectionReset();
		}
	}

	public void addClubListViewListener(final ClubListPanelListener _l){
		this.listenerList.add(ClubListPanelListener.class, _l);
	}
	
	public void removeClubListViewListener(final ClubListPanelListener _l){
		this.listenerList.remove(ClubListPanelListener.class, _l);
	}
	
	private ClubListPanelListener[] getClubListViewListener(){
		return this.listenerList.getListeners(ClubListPanelListener.class);
	}
	
	
	public static void main(String[] args) {

		// Get club manager
		final ClubModel cm = new ClubModel();
		// Create panel
		List<Club> clubList = cm.list();
		ClubListPanel listPanel = new ClubListPanel(clubList);
		System.out.println("List of clubs :");
		for (Club c : clubList) {
			System.out.println(c);
		}
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(listPanel);
		// frame.setPreferredSize(new Dimension(500, 200));
		frame.pack();
		frame.setVisible(true);
	}
	
	public Club getSelectedClub(){
		if(this.listClub.getSelectedRow() != -1){
			// Get the current selection
			final Club clubSelected = this.model.getItem(this.listClub.getSelectedRow());
			return clubSelected;
		}
		return null;
	}
}
