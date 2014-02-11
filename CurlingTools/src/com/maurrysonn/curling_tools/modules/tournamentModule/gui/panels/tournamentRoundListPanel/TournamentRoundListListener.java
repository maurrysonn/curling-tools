package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public interface TournamentRoundListListener extends EventListener {

	public void creationRound(final Round _round);
	
}
