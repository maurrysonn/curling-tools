package com.maurrysonn.curling_tools.modules.tournamentModule;

import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.DashboardControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.controlers.TournamentControler;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.views.TournamentDashboardView;
import com.maurrysonn.curling_tools.modules.tournamentModule.gui.views.TournamentHomeView;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.DashboardModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.GroupModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IDashboardModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IGroupModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.IRoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.ITournamentModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundModel;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.TournamentModel;

public class TournamentManager {

	// Singleton instance
	private static TournamentManager instance = new TournamentManager();
	
	// Models
	private ITournamentModel tournamentModel;
	private IRoundModel roundModel;
	private IGroupModel groupModel;
	private IDashboardModel dashboardModel;
	
	
	// Controlers
	private TournamentControler tournamentControler;
	private DashboardControler dashboardControler;
	
	private TournamentManager() {
		// Models
		tournamentModel = new TournamentModel();
		roundModel = new RoundModel();
		groupModel = new GroupModel();
		dashboardModel = new DashboardModel(tournamentModel, roundModel);
		// Controlers
		tournamentControler = new TournamentControler(tournamentModel);
		dashboardControler = new DashboardControler(dashboardModel);
	}

	public static TournamentManager getInstance() {
		return instance;
	}
	
	public ITournamentModel getTournamentModel() {
		return tournamentModel;
	}

	public IRoundModel getRoundModel() {
		return roundModel;
	}

	public IGroupModel getGroupModel() {
		return groupModel;
	}
	
	public IDashboardModel getDashboardModel() {
		return dashboardModel;
	}
	
	public TournamentHomeView getTournamentHomeView() {
		return tournamentControler.getTournamentHomeview();
	}
	
	public TournamentDashboardView getDashboardView() {
		return dashboardControler.getDashboardView();
	}
}
