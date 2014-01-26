package com.maurrysonn.curling_tools.modules.tournamentModule;

import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.TournamentControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.views.TournamentHomeView;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IRoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentManager {

	// Models
	private ITournamentModel tournamentModel;
	private IRoundModel roundModel;
	
	// Controlers
	private TournamentControler controler;
	
	public TournamentManager() {
		tournamentModel = new TournamentModel();
		roundModel = new RoundModel();
		controler = new TournamentControler(tournamentModel);
	}

	public ITournamentModel getTournamentModel() {
		return tournamentModel;
	}

	public IRoundModel getRoundModel() {
		return roundModel;
	}
	
	public TournamentHomeView getTournamentHomeView() {
		return controler.getTournamentHomeview();
	}
}
