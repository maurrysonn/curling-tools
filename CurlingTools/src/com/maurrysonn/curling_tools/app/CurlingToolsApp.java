package com.maurrysonn.curling_tools.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.maurrysonn.curling_tools.modules.clubModule.ClubManager;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;

public class CurlingToolsApp {

	// Main Frame
	private JFrame frame;

	// Main menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuInterrogationMark = new JMenu("?");

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
		menuBar.add(_view.getMenu());
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

		// Create menuBar
		menuBar = new JMenuBar();

		// Adding about
		menuInterrogationMark = new JMenu("?");
		menuBar.add(menuInterrogationMark);
		JMenuItem about = new JMenuItem("About Curling Tools");
		menuInterrogationMark.add(about);
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "The most powerful curling application.\r\nPowered by APAM!", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// Attach menuBar
		frame.setJMenuBar(menuBar);
	}

	private static void launch() {
		System.out.println("Loading LAF....");
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
			System.err.println("LAF Nimbus not available, sorry guy...");
		}

		// Create App
		CurlingToolsApp app = new CurlingToolsApp();
		// Show
		app.show();
	}

	public static void main(String[] args) {

		// Load SplashScreen Image
		File splashscreen = new File("splashscreen.png");

		// Create splash window
		JWindow window = new JWindow();

		// Get graphical environnement
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();

		try {
			BufferedImage myPicture = ImageIO.read(splashscreen);
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			window.getContentPane().add(picLabel);
			window.setBounds((width / 2) - (myPicture.getWidth() / 2), (height / 2) - (myPicture.getHeight() / 2), myPicture.getWidth(), myPicture.getHeight());
			window.setVisible(true);
			//TODO AM to remove
			Thread.sleep(2000);
		} catch (IOException | InterruptedException e) {
			System.err.println("Failed to load splashscreen");
		}finally{
			//launch application
			launch();
			// Disable splashscreen
			window.setVisible(false);
			window.dispose();
		}
	}
}
