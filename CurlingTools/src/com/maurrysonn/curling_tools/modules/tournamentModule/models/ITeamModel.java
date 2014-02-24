package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Team;

public interface ITeamModel {

	public Team get(final long _id);
	public List<Team> list();
	public Team add(final Team _team);
	public Team update(final Team _team);
	public Team remove(final Team _team);
	
	public void addTeamModelListener(final TeamModelListener _l);
	public void removeTeamModelListener(final TeamModelListener _l);

}
