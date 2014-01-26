package com.maurrysonn.curling_tools.modules.clubModule.gui.controlers;

import java.util.List;

import com.maurrysonn.curling_tools.core.modules.IControler;
import com.maurrysonn.curling_tools.core.modules.IModel;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;

public class ClubControler implements IControler<Club> {

	// Model of module
	private IModel<Club> clubModel;

	// Club Home View
	private ClubHomeView homeView;

	public ClubControler(final IModel<Club> _clubModel) {
		// Set model
		clubModel = _clubModel;
		// Create home view
		homeView = new ClubHomeView(this);
		// Listeners
		clubModel.addModelListener(homeView);
	}

	public ClubHomeView getClubHomeview() {
		return homeView;
	}

	@Override
	public List<Club> getList() {
		return this.clubModel.list();
	}

	@Override
	public void update(Club model) {
		// TODO Club validation
		clubModel.update(model);
	}

	@Override
	public void add(Club model) {
		// TODO Club validation
		clubModel.add(model);
	}

	@Override
	public void delete(Club model) {
		// TODO Validation
		clubModel.remove(model);
	}

}
