package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubListPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public interface ClubListPanelListener extends EventListener {

	public void clubSelectionChanged(final Club _club);
	
	public void clubSelectionReset();
	
}
