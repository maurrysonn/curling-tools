package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Team;

public interface TeamModelListener extends EventListener {

	public void teamListChanged();
	public void teamAdded(final Team _team);
	public void teamUpdated(final Team _team);
	public void teamRemoved(final Team _team);

}
