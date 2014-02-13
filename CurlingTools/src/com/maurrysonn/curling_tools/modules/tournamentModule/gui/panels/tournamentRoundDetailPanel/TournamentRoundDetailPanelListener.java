package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundDetailPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public interface TournamentRoundDetailPanelListener extends EventListener {

	public void editActionPerformed(final Round _round);
	public void deleteActionPerformed(final Round _round);
	
}
