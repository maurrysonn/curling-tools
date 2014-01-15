package com.maurrysonn.curling_tools.modules.clubModule.gui.controlers;

import java.util.List;

import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.IClubModel;

public class ClubControler {

	// Model of module
	private IClubModel clubModel;
	
	// Club Home View
	private ClubHomeView homeView;
	
	public ClubControler(final IClubModel _clubModel) {
		// Set model
		clubModel = _clubModel;
		// Create home view
		homeView = new ClubHomeView(this);
		// Listeners
		clubModel.addClubManagerListener(homeView);
	}

	public ClubHomeView getClubHomeview() {
		return homeView;
	}
	
	public List<Club> getListClub() {
		return this.clubModel.list();
	}

	public void updateClub(Club club) {
		// TODO Club validation
		clubModel.update(club);
	}

	public void addClub(Club club) {
		// TODO Club validation
		clubModel.add(club);
	}

	public void deleteClub(Club club) {
		// TODO Validation
		clubModel.remove(club);		
	}
		
}
