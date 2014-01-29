package com.maurrysonn.curling_tools.core.modules;

import javax.swing.JMenu;
import javax.swing.JPanel;

public abstract class AView extends JPanel implements IView{

	private static final long serialVersionUID = 1L;
	
	private String title;
	
	@Override
	public String getTitle(){
		return title;
	}
	
	public abstract JMenu getMenu();
}
