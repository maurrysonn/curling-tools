package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Tournament;

public class TournamentModel implements ITournamentModel {

	private EventListenerList listeners;
	
	public TournamentModel() {
		listeners = new EventListenerList();
	}
	
	@Override
	public Tournament get(long _id) {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		Tournament tournament = em.find(Tournament.class, _id);
		return tournament;
	}

	@Override
	public List<Tournament> list() {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Query
		TypedQuery<Tournament> tQuery = em.createQuery("from Tournament", Tournament.class);
		List<Tournament> results = tQuery.getResultList() ;
		// Close EntityManager
		tx.commit();
		em.close();
		return results;
	}

	@Override
	public Tournament add(Tournament _tournament) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Save club
		em.persist(_tournament);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTournamentAdded(_tournament);
		// Return new object
		return _tournament;
	}

	@Override
	public Tournament update(Tournament _tournament) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Tournament tournamentUpdated = em.merge(_tournament);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTournamentUpdated(tournamentUpdated);
		// Return object
		return tournamentUpdated;
	}

	@Override
	public Tournament remove(Tournament _tournament) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Tournament tournamentAttached = em.merge(_tournament);
		// Remove entity
		em.remove(tournamentAttached);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireTournamentRemoved(tournamentAttached);
		// Return object
		return tournamentAttached;
	}

	/*
	 * Listeners management
	 */

	@Override
	public void addTournamentModelListener(TournamentModelListener _l) {
		listeners.add(TournamentModelListener.class, _l);
	}

	@Override
	public void removeTournamentModelListener(TournamentModelListener _l) {
		listeners.remove(TournamentModelListener.class, _l);
	}
	
	private TournamentModelListener[] getTournamentModelListeners() {
		return listeners.getListeners(TournamentModelListener.class);
	}

	/*
	 * Notifications
	 */

	private void fireTournamentAdded(final Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentModel.fireTournamentAdded() - Tournament="+_tournament);
		for (final TournamentModelListener l : getTournamentModelListeners()) {
			l.tournamentAdded(_tournament);
			l.tournamentListChanged();
		}
	}

	private void fireTournamentUpdated(final Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentModel.fireTournamentUpdated() - Tournament=" + _tournament);
		for (final TournamentModelListener l : getTournamentModelListeners()) {
			l.tournamentUpdated(_tournament);
		}
	}

	private void fireTournamentRemoved(final Tournament _tournament) {
		// XXX AP - Delete print
		System.out.println("TournamentModel.fireTournamentRemoved() Tournament=" + _tournament);
		for (final TournamentModelListener l : getTournamentModelListeners()) {
			l.tournamentRemoved(_tournament);
			l.tournamentListChanged();
		}
	}

}
