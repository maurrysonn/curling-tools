package com.maurrysonn.curling_tools.modules.clubModule;

import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;
import com.maurrysonn.curling_tools.modules.clubModule.models.IClubModel;

public class ClubManager {

	// Model / Manager
	final IClubModel model;
	
	// Controler
	final ClubControler controler;
	
	public ClubManager() {
		model = new ClubModel();
		controler = new ClubControler(model);
	}
	
	public ClubHomeView getClubHomeView(){
		return controler.getClubHomeview();
	}
}
