package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Round;

public class RoundModel implements IRoundModel {

	private EventListenerList listeners;
	
	public RoundModel() {
		listeners = new EventListenerList();
	}
	
	@Override
	public Round get(long _id) {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		Round round = em.find(Round.class, _id);
		return round;
	}

	@Override
	public List<Round> list() {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Query
		TypedQuery<Round> tQuery = em.createQuery("from Round", Round.class);
		List<Round> results = tQuery.getResultList() ;
		// Close EntityManager
		tx.commit();
		em.close();
		return results;
	}
	
	@Override
	public Round add(Round _round) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Save club
		em.persist(_round);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireRoundAdded(_round);
		// Return new object
		return _round;
	}

	@Override
	public Round update(Round _round) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Round roundUpdated = em.merge(_round);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireRoundUpdated(_round);
		// Return object
		return roundUpdated;
	}

	@Override
	public Round remove(Round _round) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Round roundAttached = em.merge(_round);
		// Remove entity
		em.remove(roundAttached);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireRoundRemoved(_round);
		// Return object
		return roundAttached;
	}

	/*
	 * Listeners management
	 */

	@Override
	public void addRoundModelListener(RoundModelListener _l) {
		listeners.add(RoundModelListener.class, _l);
	}

	@Override
	public void removeRoundModelListener(RoundModelListener _l) {
		listeners.remove(RoundModelListener.class, _l);
	}

	private RoundModelListener[] getRoundModelListeners() {
		return listeners.getListeners(RoundModelListener.class);
	}
	
	/*
	 * Notifications
	 */

	private void fireRoundAdded(final Round _round) {
		// XXX AP - Delete print
		System.out.println("RoundModel.fireRoundAdded() - Round = " + _round);
		for (final RoundModelListener l : getRoundModelListeners()) {
			l.roundAdded(_round);
			l.roundListChanged();
		}
	}

	private void fireRoundUpdated(final Round _round) {
		// XXX AP - Delete print
		System.out.println("RoundModel.fireRoundUpdated() - Round = " + _round);
		for (final RoundModelListener l : getRoundModelListeners()) {
			l.roundUpdated(_round);
		}
	}

	private void fireRoundRemoved(final Round _round) {
		// XXX AP - Delete print
		System.out.println("RoundModel.fireRoundRemoved() - Round = " + _round);
		for (final RoundModelListener l : getRoundModelListeners()) {
			l.roundRemoved(_round);
			l.roundListChanged();
		}
	}
	
}
