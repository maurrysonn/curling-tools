package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface TournamentModelListener extends EventListener {

	public void tournamentListChanged();
	public void tournamentAdded(final Tournament _tournament);
	public void tournamentUpdated(final Tournament _tournament);
	public void tournamentRemoved(final Tournament _tournament);
	
}
