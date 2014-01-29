package com.maurrysonn.curling_tools.modules.clubModule;

import java.util.ArrayList;
import java.util.List;

import com.maurrysonn.curling_tools.core.modules.AModule;
import com.maurrysonn.curling_tools.core.modules.AView;
import com.maurrysonn.curling_tools.core.modules.IAction;
import com.maurrysonn.curling_tools.core.modules.IControler;
import com.maurrysonn.curling_tools.core.modules.IModel;
import com.maurrysonn.curling_tools.core.modules.IModelListener;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;

public class ClubModule extends AModule {

	static ClubModule instance;

	private ClubModule(int _id, String _name, String _verboseName, List<AView> _viewsList, List<IAction> _actionsList) {
		super(_id, _name, _verboseName, _viewsList, _actionsList);
	}

	public static ClubModule initialize() {
		List<AView> clubViews = new ArrayList<AView>();
		IModel<Club> model = new ClubModel();
		IControler<Club> controler = new ClubControler(model);
		clubViews.add(new ClubHomeView(controler));
		model.addModelListener((IModelListener<Club>) clubViews.get(0));

		if (instance == null)
			instance = new ClubModule(1, "Club", "Gestion des club", clubViews, null);
		return instance;
	}
}
