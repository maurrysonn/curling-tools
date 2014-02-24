package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Team;

public class TeamModel implements ITeamModel {

	private EventListenerList listeners;

	public TeamModel() {
		listeners = new EventListenerList();
	}
	
	@Override
	public Team get(long _id) {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		Team team = em.find(Team.class, _id);
		return team;
	}

	@Override
	public List<Team> list() {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Query
		TypedQuery<Team> tQuery = em.createQuery("from Team", Team.class);
		List<Team> results = tQuery.getResultList() ;
		// Close EntityManager
		tx.commit();
		em.close();
		return results;
	}

	@Override
	public Team add(Team _team) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Save club
		em.persist(_team);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTeamAdded(_team);
		// Return new object
		return _team;
	}

	@Override
	public Team update(Team _team) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Team teamUpdated = em.merge(_team);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTeamUpdated(teamUpdated);
		// Return object
		return teamUpdated;
	}

	@Override
	public Team remove(Team _team) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Team teamAttached = em.merge(_team);
		// Remove entity
		em.remove(teamAttached);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTeamRemoved(teamAttached);
		// Return object
		return teamAttached;
	}

	/*
	 * Listeners management
	 */
	@Override
	public void addTeamModelListener(TeamModelListener _l) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeTeamModelListener(TeamModelListener _l) {
		// TODO Auto-generated method stub
		
	}

	private TeamModelListener[] getTeamModelListerners() {
		return listeners.getListeners(TeamModelListener.class);
	}

	/*
	 * Events management
	 */
	private void fireTeamAdded(Team _team) {
		for (final TeamModelListener l : getTeamModelListerners()) {
			l.teamAdded(_team);
			l.teamListChanged();
		}
	}


	private void fireTeamUpdated(Team teamUpdated) {
		for (final TeamModelListener l : getTeamModelListerners()) {
			l.teamUpdated(teamUpdated);
		}
	}
	
	private void fireTeamRemoved(Team teamAttached) {
		for (final TeamModelListener l : getTeamModelListerners()) {
			l.teamRemoved(teamAttached);
			l.teamListChanged();
		}
	}


}
