package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.groupEditPanel.GroupEditPanel;

public class GroupFormDialog extends JDialog {

	private static final long serialVersionUID = -8845219300141001976L;

	private GroupEditPanel contentPanel;
	private JButton actionBtn;
	private JButton cancelBtn;

	private boolean creationMode;

	private Group data;

	public GroupFormDialog(final JPanel _parent) {
		this(_parent, null);
	}

	public GroupFormDialog(final JPanel _parent, final Group _group) {
		super();
		setModal(true);
		creationMode = (_group == null);
		data = null;
		initGUI(_group);
		initListeners();
		pack();
	}

	private void initListeners() {
		actionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data = contentPanel.getGroupUpdated();
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

	private void initGUI(final Group _group) {
		contentPanel = new GroupEditPanel(_group);
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

	public Group getGroup() {
		return data;
	}
}
