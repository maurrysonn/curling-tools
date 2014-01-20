package com.maurrysonn.curling_tools.modules.tournamentModule;

import com.maurrysonn.curling_tools.modules.tournamentModule.models.IRoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentManager {

	private ITournamentModel tounramentModel;
	private IRoundModel roundModel;
	
	public TournamentManager() {
		tounramentModel = new TournamentModel();
		roundModel = new RoundModel();
	}

	public ITournamentModel getTournamentModel() {
		return tounramentModel;
	}
	
	public IRoundModel getRoundModel() {
		return roundModel;
	}
}
