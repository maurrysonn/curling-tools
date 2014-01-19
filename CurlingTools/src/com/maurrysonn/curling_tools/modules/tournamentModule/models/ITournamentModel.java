package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public interface ITournamentModel {

	public Tournament get(final long _id);
	public List<Tournament> list();
	public Tournament add(final Tournament _tournament);
	public Tournament update(final Tournament _tournament);
	public Tournament remove(final Tournament _tournament);
	
	public void addTournamentModelListener(final TournamentModelListener _l);
	public void removeTournamentModelListener(final TournamentModelListener _l);

}
