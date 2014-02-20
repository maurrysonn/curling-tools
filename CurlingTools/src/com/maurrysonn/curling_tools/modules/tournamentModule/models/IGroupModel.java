package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;

public interface IGroupModel {
	
	public Group get(final long _id);
	public List<Group> list();
	public Group add(final Group _group);
	public Group update(final Group _group);
	public Group remove(final Group _group);
	
	public void addGroupModelListener(final GroupModelListener _l);
	public void removeGroupModelListener(final GroupModelListener _l);

}
