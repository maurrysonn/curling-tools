package com.maurrysonn.curling_tools.core.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUIUtils {

	public static void invokeLaterInEDT(final Runnable doRun) {
		// Check if the current thread is EDT
		if(SwingUtilities.isEventDispatchThread()) {
			// Execution of runnable's instructions
			doRun.run();
		} else {
			// Post runnable in the EDT queue.
			SwingUtilities.invokeLater(doRun);
		}
	}
	
	public static void invokeLaterOutOfEDT(final Runnable doRun){
		// Check if the current thread is EDT
		if(SwingUtilities.isEventDispatchThread()) {
			// In EDT : execution of instructions in new thread
			Thread t = new Thread(doRun);
			t.start();
		} else {
			// Out of EDT : execution of runnable's instructions
			doRun.run();
		}
	}
	
	public static void printThreadInfos() {
		System.out.println("CURRENT THREAD = " + Thread.currentThread() + " - EDT = " + SwingUtilities.isEventDispatchThread());
	}
	
	public static JFrame createFrameForTest() {
		// Create frame
		final JFrame frame = new JFrame();
		frame.setSize(new Dimension(800, 500));
		frame.setTitle("Curling Tools - Test");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("==> FrameTest.terminate()");
				// Closing persistence connections
				PersistenceUtils.finalizePersistence();
				// Quit program
				System.exit(0);
			}
		});
		// Center on screen
		frame.setLocationRelativeTo(null);
		// Layout
		frame.getContentPane().setLayout(new BorderLayout());
		
		return frame;
	}
	
	
}
