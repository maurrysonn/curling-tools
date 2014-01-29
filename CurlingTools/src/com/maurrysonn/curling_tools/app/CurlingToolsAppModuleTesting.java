package com.maurrysonn.curling_tools.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.maurrysonn.curling_tools.core.modules.AModule;
import com.maurrysonn.curling_tools.modules.clubModule.ClubModule;

public class CurlingToolsAppModuleTesting {

	// Main Frame
	private JFrame frame;

	// Main menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuInterrogationMark = new JMenu("?");

	// Module Manager
	private List<AModule> modulesList;

	// Splashscreen window
	private JWindow window;

	public CurlingToolsAppModuleTesting(JWindow _window) {
		// Store splashscreen window
		window = _window;
		// Initialize modules list
		modulesList = new ArrayList<AModule>();
		// Registrering available module
		registerModules();
		// Initialize main GUI
		initializationGUI();
		// Load registred modules
		loadRegistredModule();
	}

	private void registerModules() {
		registerModule(ClubModule.getInstance());
	}

	private void registerModule(AModule module) {
		modulesList.add(module);
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

	private void loadRegistredModule() {
		for (final AModule currentModule : modulesList) {
			final JButton tmp = new JButton(currentModule.getName());
			tmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.add(currentModule.getViewsList().get(0));
					menuBar.add(currentModule.getViewsList().get(0).getMenu());
					tmp.setVisible(false);
				}
			});
			frame.add(tmp);
		}
	}

	private void show() {
		// Show frame
		frame.pack();
		frame.setVisible(true);
	}

	private static void launch(JWindow _window) {
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
		CurlingToolsAppModuleTesting app = new CurlingToolsAppModuleTesting(_window);
		// Show
		app.show();
	}

	public static void main(String[] args) {

		// Load SplashScreen Image
		File splashscreen = new File("CurlingTools/images/splashscreen.png");

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
		} catch (IOException e) {
			System.err.println("Failed to load splashscreen");
		} finally {
			// launch application
			launch(window);
			// Disable splashscreen
			window.setVisible(false);
			window.dispose();
		}
	}
}
