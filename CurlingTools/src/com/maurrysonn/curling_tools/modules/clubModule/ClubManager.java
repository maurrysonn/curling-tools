package com.maurrysonn.curling_tools.modules.clubModule;

import com.maurrysonn.curling_tools.core.modules.IModel;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;

public class ClubManager {

	// Model
	final IModel<Club> model;

	// Controler
	final ClubControler controler;

	public ClubManager() {
		model = new ClubModel();
		controler = new ClubControler(model);
	}

	public ClubHomeView getClubHomeView() {
		return controler.getClubHomeview();
	}
}
