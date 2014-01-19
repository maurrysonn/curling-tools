package com.maurrysonn.curling_tools.modules.clubModule.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;
import com.maurrysonn.curling_tools.modules.clubModule.models.IClubModel;

public class ClubManagementTest {

	public static void main(String[] args) {
		// XXX amaury - Delete print
		System.out.println("+++++ Starting ClubManagementTest +++++");
		
		// Model / Manager
		final IClubModel manager = new ClubModel();
		// Controler
		final ClubControler controler = new ClubControler(manager);
		// Club Home View
		final ClubHomeView view = new ClubHomeView(controler);
		// Listeners
		manager.addClubModelListener(view);
		
		// Frame
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(500, 500));
		frame.setTitle("ClubManagementTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(view.getContainer(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
}
