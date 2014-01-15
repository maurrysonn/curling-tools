package com.maurrysonn.curling_tools.modules.clubModule.models;

import java.util.EventListener;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public interface ClubModelListener extends EventListener{

	public void clubListChanged();
	public void clubAdded(final Club _club);
	public void clubUpdated(final Club _club);
	public void clubRemoved(final Club _club);
	
}
