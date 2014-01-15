package com.maurrysonn.curling_tools.modules.clubModule.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubEditPanel.ClubEditPanel;

public class ClubFormDialog extends JDialog {

	private static final long serialVersionUID = -8845219300141001976L;

	private ClubEditPanel contentPanel;
	private JButton actionBtn;
	private JButton cancelBtn;
	
	private boolean creationMode;
	
	private Club data;
	
	public ClubFormDialog(final JPanel _parent){
		this(_parent, null);
	}
	
	public ClubFormDialog(final JPanel _parent, final Club _club) {
		// TODO Dialog parent
		super();
		setModal(true);
		creationMode = (_club == null);
		data = null;
		initGUI(_club);
		initListeners();
		pack();
	}
	
	private void initListeners() {
		actionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data = contentPanel.getClubUpdated();
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

	private void initGUI(final Club _club){
		contentPanel = new ClubEditPanel(_club);
		if(creationMode){
			this.setTitle("Creation");
			actionBtn = new JButton("Create");
		}else{
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
	
	public Club getClub(){
		return data;
	}
}
