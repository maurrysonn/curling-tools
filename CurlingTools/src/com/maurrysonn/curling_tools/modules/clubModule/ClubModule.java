package com.maurrysonn.curling_tools.modules.clubModule;

import com.maurrysonn.curling_tools.core.modules.AModule;
import com.maurrysonn.curling_tools.core.modules.AView;
import com.maurrysonn.curling_tools.core.modules.IControler;
import com.maurrysonn.curling_tools.core.modules.IModel;
import com.maurrysonn.curling_tools.core.modules.IModelListener;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;
import com.maurrysonn.curling_tools.modules.clubModule.gui.controlers.ClubControler;
import com.maurrysonn.curling_tools.modules.clubModule.gui.views.ClubHomeView;
import com.maurrysonn.curling_tools.modules.clubModule.models.ClubModel;

public class ClubModule extends AModule {
	private static final ClubModule instance = new ClubModule(1, "Club", "Gestion des club");

	private ClubModule(final int _id, final String _name, final String _verboseName) {
		super(_id, _name, _verboseName);
		System.out.println("ClubModule.ClubModule() Generate instance");
		// Create model
		IModel<Club> model = new ClubModel();
		// Create controler
		IControler<Club> controler = new ClubControler(model);
		// Creating View
		AView view1 = new ClubHomeView(controler);
		addView(view1);
		// Link modelListener
		model.addModelListener((IModelListener<Club>) view1);
	}

	public static ClubModule getInstance() {
		return ClubModule.instance;
	}
}
