package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class DashboardModel implements IDashboardModel {

	private Tournament tournament;
	
	private EventListenerList listeners;
	
	public DashboardModel() {
		this(null, null);
	}

	public DashboardModel(final ITournamentModel _tournamentModel, final IRoundModel _roundModel) {
		listeners = new EventListenerList();
		setTournament(null);
		initializeListener(_tournamentModel);
		initializeListener(_roundModel);
	}
	
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(final Tournament _tournament) {
		tournament = _tournament;
		if(tournament != null) {
			fireDashboardNewTournamentSelected(_tournament);
		} else {
			fireDashboardTournamentReset();
		}
	}
	
	private void initializeListener(final ITournamentModel _tournamentModel) {
		if(_tournamentModel != null) {
			_tournamentModel.addTournamentModelListener(new TournamentModelListener() {

				@Override
				public void tournamentUpdated(Tournament _tournament) {
					if(tournament != null && _tournament.equals(_tournament)) {
						// Update dashboard tournament
						tournament = _tournament;
						// Fire modifications
						fireDashboardTournamentUpdated(_tournament);
					}
				}

				@Override
				public void tournamentRemoved(Tournament _tournament) {
					if(tournament != null && _tournament.equals(_tournament)) {
						setTournament(null);
					}
				}

				@Override
				public void tournamentListChanged() {
					// Do nothing
				}

				@Override
				public void tournamentAdded(Tournament _tournament) {
					// Do nothing
				}
			});
		}
	}
	
	private void initializeListener(final IRoundModel _roundModel) {
		if(_roundModel != null) {
			_roundModel.addRoundModelListener(new RoundModelListener() {

				@Override
				public void roundUpdated(Round _round) {
					if(tournament != null && tournament.equals(_round.getTournament())) {
						fireDashboardTournamentRoundUpdated(_round);
					}
				}
				
				@Override
				public void roundRemoved(Round _round) {
					if(tournament != null && tournament.equals(_round.getTournament())) {
						fireDashboardTournamentRoundRemoved(_round);
					}					
				}
				
				@Override
				public void roundListChanged() {
					// Do nothing
				}
				
				@Override
				public void roundAdded(Round _round) {
					if(tournament != null && tournament.equals(_round.getTournament())) {
						fireDashboardTournamentRoundAdded(_round);
					}					
				}
			});
		}
	}
	
	private void fireDashboardTournamentReset() {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentReset();
		}
	}

	private void fireDashboardNewTournamentSelected(final Tournament _tournament) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardNewTournamentSelected(_tournament);
		}
	}
	
	private void fireDashboardTournamentUpdated(final Tournament _tournament) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentUpdated(_tournament);
		}
	}

	private void fireDashboardTournamentRoundAdded(final Round _round) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentRoundAdded(_round);
		}
	}

	private void fireDashboardTournamentRoundUpdated(final Round _round) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentRoundUpdated(_round);
		}
	}

	private void fireDashboardTournamentRoundRemoved(final Round _round) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.DashboardTournamentRoundRemoved(_round);
		}
	}
	
	private DashboardModelListener[] getDashboardTournamentListeners() {
		return listeners.getListeners(DashboardModelListener.class);
	}

	@Override
	public void addDashboardModelListener(final DashboardModelListener l) {
		listeners.add(DashboardModelListener.class, l);
	}

	@Override
	public void removeDashboardModelListener(final DashboardModelListener l) {
		listeners.remove(DashboardModelListener.class, l);
	}	
}
