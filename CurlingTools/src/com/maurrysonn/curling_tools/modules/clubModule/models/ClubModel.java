package com.maurrysonn.curling_tools.modules.clubModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public class ClubModel implements IClubModel {

	private EventListenerList listeners;
	
	public ClubModel() {
		listeners = new EventListenerList();
	}
	
	@Override
	public Club get(final long _id){
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		Club club = em.find(Club.class, _id);
		return club;
	}
	
	@Override
	public List<Club> list(){
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Query
		TypedQuery<Club> tQuery = em.createQuery("from Club", Club.class);
		List<Club> results = tQuery.getResultList() ;
		// Close EntityManager
		tx.commit();
		em.close();
		return results;
	}
	
	@Override
	public Club add(final Club _club){
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Save club
		em.persist(_club);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		this.fireClubAdded(_club);
		// Return new object
		return _club;
	}
	
	@Override
	public Club update(final Club _club){
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Club clubUpdated = em.merge(_club);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		this.fireClubUpdated(clubUpdated);
		// Return object
		return clubUpdated;
	}
	
	@Override
	public Club remove(final Club _club){
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Club clubAttached = em.merge(_club);
		// Remove entity
		em.remove(clubAttached);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		this.fireClubRemoved(clubAttached);
		// Return object
		return clubAttached;
	}

	/*
	 * Listeners management
	 */

	@Override
	public void addClubModelListener(ClubModelListener _l) {
		listeners.add(ClubModelListener.class, _l);
	}

	@Override
	public void removeClubModelListener(ClubModelListener _l) {
		listeners.remove(ClubModelListener.class, _l);
	}
	
	private ClubModelListener[] getClubManagerListeners(){
		return listeners.getListeners(ClubModelListener.class);
	}
	
	/*
	 * Notifications
	 */
	
	private void fireClubAdded(final Club _clubAdded){
		// XXX amaury - Delete print
		System.out.println("ClubManager.notifyClubAdded() - Club="+_clubAdded);
		for(final ClubModelListener l : this.getClubManagerListeners()){
			l.clubAdded(_clubAdded);
			l.clubListChanged();
		}
	}

	private void fireClubUpdated(final Club _clubUpdated){
		// XXX amaury - Delete print
		System.out.println("ClubManager.notifyClubUpdated() - Club="+_clubUpdated);
		for(final ClubModelListener l : this.getClubManagerListeners()){
			l.clubUpdated(_clubUpdated);
			l.clubListChanged();
		}
	}
	
	private void fireClubRemoved(final Club _clubRemoved){
		// XXX amaury - Delete print
		System.out.println("ClubManager.notifyClubRemoved() - Club="+_clubRemoved);
		for(final ClubModelListener l : this.getClubManagerListeners()){
			l.clubRemoved(_clubRemoved);
			l.clubListChanged();
		}
	}
	
}
