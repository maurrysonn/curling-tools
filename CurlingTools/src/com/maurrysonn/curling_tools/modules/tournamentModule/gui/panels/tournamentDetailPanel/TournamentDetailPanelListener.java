package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentDetailPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface TournamentDetailPanelListener extends EventListener {

	public void tournamentChanged(final Tournament _tournament);
	
	public void tournamentReset();
	
}
