package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public interface IRoundModel {

	public Round get(final long _id);
	public List<Round> list();
	public Round add(final Round _round);
	public Round update(final Round _round);
	public Round remove(final Round _round);
	
	public void addRoundModelListener(final RoundModelListener _l);
	public void removeRoundModelListener(final RoundModelListener _l);

}
