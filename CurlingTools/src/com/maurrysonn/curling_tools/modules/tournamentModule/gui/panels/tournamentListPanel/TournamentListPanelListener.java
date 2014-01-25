package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentListPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface TournamentListPanelListener extends EventListener {

	public void tournamentSelectionChanged(final Tournament _tournament);
	
	public void tournamentSelectionReset();
	
}
