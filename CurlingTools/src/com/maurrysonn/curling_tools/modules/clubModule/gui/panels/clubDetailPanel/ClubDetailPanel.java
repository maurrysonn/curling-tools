package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubDetailPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;

public class ClubDetailPanel extends JPanel {

	private static final long serialVersionUID = 5869075635006515067L;
	
	private Club club;
	
	private JLabel nameClub;
	private JLabel shortNameClub;
	
	public ClubDetailPanel() {
		// Initialize item
		this.club = null;
		// IHM
		createIHM();
	}

	private void createIHM() {
		this.setPreferredSize(new Dimension(300, 100));
		
		// Labels
		JLabel nameLabel = new JLabel("Name :");
		JLabel shortNameLabel = new JLabel("Short Name :");
		// Values
		nameClub = new JLabel("");
		shortNameClub = new JLabel("");
		
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
		this.add(nameClub, gbc);
		gbc.gridy = 1;
		this.add(shortNameClub, gbc);
		// TODO - Remove Debug
		this.setBorder(BorderFactory.createLineBorder(Color.orange));
	}

	public Club getClub() {
		return club;
	}
	
	public boolean isClubDisplayed(){
		return (this.club != null);
	}

	public void setClub(Club _club) {
		// Update model
		this.club = _club;
		// Update view
		this.updateView();
		// Notifications
		if(_club != null){
			notifyClubChanged(_club);
		}else{
			notifyClubReset();
		}
	}

	public void resetClub(){
		this.setClub(null);
	}
	
	private void updateView(){
		// TODO AP - EDT
		if(this.club != null){
			this.nameClub.setText(this.club.getName());
			this.shortNameClub.setText(this.club.getShortName());
		}else{
			this.nameClub.setText("");
			this.shortNameClub.setText("");
		}
	}
	
	public void addClubDetailViewListener(final ClubDetailPanelListener _l){
		this.listenerList.add(ClubDetailPanelListener.class, _l);
	}
	
	public void removeClubDetailViewListener(final ClubDetailPanelListener _l){
		this.listenerList.remove(ClubDetailPanelListener.class, _l);
	}
	
	private ClubDetailPanelListener[] getClubDetailListeners(){
		return this.listenerList.getListeners(ClubDetailPanelListener.class);
	}
	
	private void notifyClubChanged(final Club _club){
		for (final ClubDetailPanelListener l : getClubDetailListeners()) {
			l.clubChanged(_club);
		}
	}

	private void notifyClubReset(){
		for (final ClubDetailPanelListener l : getClubDetailListeners()) {
			l.clubReset();
		}
	}
	
	public static void main(String[] args) {
		// Get club manager
		final ClubModel cm = new ClubModel();
		// Create panel
		List<Club> clubList = cm.list();
		ClubDetailPanel panel = new ClubDetailPanel();
		Club club = clubList.get(0);
		panel.setClub(club);
		// XXX amaury - Delete print
		System.out.println("Club displayed = " + club);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		// frame.setPreferredSize(new Dimension(500, 200));
		frame.pack();
		frame.setVisible(true);

	}
}
