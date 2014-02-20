package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;

public interface GroupModelListener extends EventListener {

	public void groupListChanged();
	public void groupAdded(final Group _group);
	public void groupUpdated(final Group _group);
	public void groupRemoved(final Group _group);

}
