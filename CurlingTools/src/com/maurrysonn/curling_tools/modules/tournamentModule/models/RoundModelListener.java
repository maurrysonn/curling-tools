package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public interface RoundModelListener extends EventListener {

	public void roundListChanged();
	public void roundAdded(final Round _round);
	public void roundUpdated(final Round _round);
	public void roundRemoved(final Round _round);
	
}
