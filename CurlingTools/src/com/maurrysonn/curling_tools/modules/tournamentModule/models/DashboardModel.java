package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class DashboardModel implements IDashboardModel {

	private Tournament tournament;
	
	private EventListenerList listeners;
	
	public DashboardModel() {
		this(null, null, null);
	}

	public DashboardModel(final ITournamentModel _tournamentModel, final IRoundModel _roundModel, final IGroupModel _groupModel) {
		listeners = new EventListenerList();
		setTournament(null);
		initializeListener(_tournamentModel);
		initializeListener(_roundModel);
		initializeListener(_groupModel);
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
	
	private void initializeListener(final IGroupModel _groupModel) {
		if(_groupModel != null) {
			_groupModel.addGroupModelListener(new GroupModelListener() {
				
				@Override
				public void groupUpdated(Group _group) {
					// XXX amaury - Delete print
					System.out
							.println("DashboardModel.initializeListener(...).new GroupModelListener() {...}.groupUpdated() - " + _group);
					fireDashboardGroupUpdated(_group);
				}
				
				@Override
				public void groupRemoved(Group _group) {
					fireDashboardGroupRemoved(_group);
					
				}
				
				@Override
				public void groupListChanged() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void groupAdded(Group _group) {
					// XXX amaury - Delete print
					System.out
							.println("DashboardModel.initializeListener(...).new GroupModelListener() {...}.groupAdded() - " + _group);
					fireDashboardGroupAdded(_group);
				}
			});
		}
	}
	
	
	@Override
	public void addTournamentRound(final Round _round) {
		// Check if tournament selected
		if(tournament != null) {
			// Creation of round
			tournament.addRound(_round);
		}
	}

	@Override
	public void updateTournamentRound(Round _round) {
		// Check if tournament selected
		if(tournament != null) {
			// Creation of round
			tournament.updateRound(_round);
		}
	}
	
	@Override
	public void removeTournamentRound(Round _round) {
		// Check if tournament is selected
		if(tournament != null) {
			// Deletion of round
			tournament.removeRound(_round);
		}
	}
	
	@Override
	public void addGroup(Group _group, Round _round) {
		if(tournament != null && _round != null && _round.getTournament().equals(tournament)) {
			_round.addGroup(_group);
		}
	}
	
	@Override
	public void updateGroup(Group _group) {
		if(tournament != null && _group != null && _group.getRound().getTournament().equals(tournament)) {
			_group.getRound().updateGroup(_group);
		}
	}
	
	@Override
	public void deleteGroup(Group _group) {
		if(tournament != null && _group != null && _group.getRound().getTournament().equals(tournament)) {
			_group.getRound().removeGroup(_group);
		}
	}

	
	/*
	 * EVENTS
	 */
	
	private void fireDashboardTournamentReset() {
		// XXX amaury - Delete print
		System.out.println("DashboardModel.fireDashboardTournamentReset()");
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentReset();
		}
	}

	private void fireDashboardNewTournamentSelected(final Tournament _tournament) {
		// XXX amaury - Delete print
		System.out
				.println("DashboardModel.fireDashboardNewTournamentSelected() - " + _tournament);
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardNewTournamentSelected(_tournament);
		}
	}
	
	private void fireDashboardTournamentUpdated(final Tournament _tournament) {
		// XXX amaury - Delete print
		System.out.println("DashboardModel.fireDashboardTournamentUpdated() - " + _tournament);
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentUpdated(_tournament);
		}
	}

	private void fireDashboardTournamentRoundAdded(final Round _round) {
		// XXX amaury - Delete print
		System.out
				.println("DashboardModel.fireDashboardTournamentRoundAdded() - " + _round);
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentRoundAdded(_round);
		}
	}

	private void fireDashboardTournamentRoundUpdated(final Round _round) {
		// XXX amaury - Delete print
		System.out
				.println("DashboardModel.fireDashboardTournamentRoundUpdated() - " + _round);
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardTournamentRoundUpdated(_round);
		}
	}

	private void fireDashboardTournamentRoundRemoved(final Round _round) {
		// XXX amaury - Delete print
		System.out
				.println("DashboardModel.fireDashboardTournamentRoundRemoved() - " + _round);
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.DashboardTournamentRoundRemoved(_round);
		}
	}
	
	private void fireDashboardGroupAdded(Group _group) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardGroupAdded(_group);
		}
	}

	private void fireDashboardGroupUpdated(Group _group) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardGroupUpdated(_group);
		}
	}
	
	private void fireDashboardGroupRemoved(Group _group) {
		for (final DashboardModelListener l : getDashboardTournamentListeners()) {
			l.dashboardGroupRemoved(_group);
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
