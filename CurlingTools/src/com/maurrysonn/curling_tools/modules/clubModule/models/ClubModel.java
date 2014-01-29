package com.maurrysonn.curling_tools.modules.clubModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.maurrysonn.curling_tools.core.modules.AModel;
import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.clubModule.entities.Club;

public class ClubModel extends AModel<Club> {
	
	public ClubModel() {

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
		this.fireAdded(_club);
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
		this.fireUpdated(clubUpdated);
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
		this.fireRemoved(clubAttached);
		// Return object
		return clubAttached;
	}

	@Override
	public void registerModel(Club model) {
		//TODO check bdd integrity
		//TODO Create Table
		//...
	}
}
