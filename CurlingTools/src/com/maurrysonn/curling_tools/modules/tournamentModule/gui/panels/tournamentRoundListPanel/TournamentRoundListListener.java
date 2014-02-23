package com.maurrysonn.curling_tools.modules.tournamentModule.gui.panels.tournamentRoundListPanel;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public interface TournamentRoundListListener extends EventListener {

	public void creationRoundPerformed(final Round _round);
	public void editionRoundPerformed(final Round _round);
	public void deletionRoundPerformed(final Round _round);
	
	public void creationGroupPerformed(final Group _group, final Round _round);
	public void editionGroupPerformed(final Group _group);
	public void deletionGroupPerformed(final Group _group);
	
}
