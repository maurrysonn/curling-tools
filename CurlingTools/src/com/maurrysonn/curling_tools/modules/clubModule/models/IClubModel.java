package com.maurrysonn.curling_tools.modules.clubModule.models;

import java.util.List;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public interface IClubModel {

	public Club get(final long _id);
	public List<Club> list();
	public Club add(final Club _club);
	public Club update(final Club _club);
	public Club remove(final Club _club);
	
	public void addClubModelListener(final ClubModelListener _l);
	public void removeClubModelListener(final ClubModelListener _l);
}
