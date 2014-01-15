package com.maurrysonn.curling_tools.app;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.maurrysonn.curling_tools.modules.clubModule.ClubManager;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;

public class CurlingToolsApp {

	
	// Main Frame
	private JFrame frame;
	
	// Module Managers
	private ClubManager clubManager;
	
	
	public CurlingToolsApp() {
		// Starting modules
		initializationModules();
		// Initialization GUI
		initializationGUI();
		// Set view
		setView(clubManager.getClubHomeView());
	}

	public void show() {
		// Shaw frame
		frame.pack();
		frame.setVisible(true);
	}

	private void setView(final ClubHomeView _view) {
		// Select club home view
		frame.getContentPane().add(_view.getContainer(), BorderLayout.CENTER);
		_view.initializeData();
	}

	private void initializationModules() {
		// Initialization Modules
		clubManager = new ClubManager();
	}

	private void initializationGUI() {
		// Create frame
		frame = new JFrame();
		frame.setSize(new Dimension(800, 500));
		frame.setTitle("Curling Tools");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center on screen
		frame.setLocationRelativeTo(null);
		// Layout
		frame.getContentPane().setLayout(new BorderLayout());
	}

	
	public static void main(String[] args) {

		System.out.println("Loading LAF....");
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
			System.err.println("LAF Nimbus not available, sorry guy...");
		}
		
		// Create App
		CurlingToolsApp app = new CurlingToolsApp();
		// Show
		app.show();
		
	}
	
}
