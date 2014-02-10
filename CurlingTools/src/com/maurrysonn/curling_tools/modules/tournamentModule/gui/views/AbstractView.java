package com.maurrysonn.curling_tools.modules.tournamentModule.gui.views;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JPanel;

public abstract class AbstractView {
	
	// Panel container
	protected JPanel container;
	// Menu bar
	protected JMenu menu;

	public AbstractView() {
		container = new JPanel(new BorderLayout());
		menu = new JMenu(getMenuVerboseName());
	}
	
	
	public JPanel getContainer() {
		return container;
	}
	
	public JMenu getMenu() {
		return menu;
	}

	public abstract void initializeData();

	protected String getMenuVerboseName() {
		return getClass().getSimpleName();
	}
}
