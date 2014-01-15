package com.maurrysonn.curling_tools.modules.clubModule.gui.panels.clubDetailPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public interface ClubDetailPanelListener extends EventListener {

	public void clubChanged(final Club _club);
	
	public void clubReset();
	
}
