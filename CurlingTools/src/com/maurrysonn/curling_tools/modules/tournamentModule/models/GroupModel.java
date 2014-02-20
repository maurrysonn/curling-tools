package com.maurrysonn.curling_tools.modules.tournamentModule.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;
import com.maurrysonn.curling_tools.modules.tournamentModule.entities.Group;

public class GroupModel implements IGroupModel {

	private EventListenerList listeners;
	
	public GroupModel() {
		listeners = new EventListenerList();
	}
	
	@Override
	public Group get(long _id) {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		Group group = em.find(Group.class, _id);
		return group;
	}

	@Override
	public List<Group> list() {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Query
		TypedQuery<Group> tQuery = em.createQuery("from Group", Group.class);
		List<Group> results = tQuery.getResultList() ;
		// Close EntityManager
		tx.commit();
		em.close();
		return results;
	}

	@Override
	public Group add(Group _group) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Save Group
		em.persist(_group);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireGroupAdded(_group);
		// Return new object
		return _group;
	}

	@Override
	public Group update(Group _group) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Group groupUpdated = em.merge(_group);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireGroupUpdated(groupUpdated);
		// Return object
		return groupUpdated;
	}


	@Override
	public Group remove(Group _group) {
		// Get EntityManager and start transaction
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Group groupAttached = em.merge(_group);
		// Remove entity
		em.remove(groupAttached);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
		// Notification
		fireGroupRemoved(groupAttached);
		// Return object
		return groupAttached;
	}

	/*
	 * Listeners management
	 */

	@Override
	public void addGroupModelListener(final GroupModelListener _l) {
		listeners.add(GroupModelListener.class, _l);
	}

	@Override
	public void removeGroupModelListener(GroupModelListener _l) {
		listeners.remove(GroupModelListener.class, _l);
	}

	private GroupModelListener[] getGroupModelListeners() {
		return listeners.getListeners(GroupModelListener.class);
	}
	
	/*
	 * Events
	 */

	private void fireGroupAdded(final Group _group) {
		for (final GroupModelListener l : getGroupModelListeners()) {
			l.groupAdded(_group);
			l.groupListChanged();
		}
	}

	private void fireGroupUpdated(final Group _group) {
		for (final GroupModelListener l : getGroupModelListeners()) {
			l.groupUpdated(_group);
		}
	}
	
	private void fireGroupRemoved(final Group _group) {
		for (final GroupModelListener l : getGroupModelListeners()) {
			l.groupRemoved(_group);
			l.groupListChanged();
		}
	}
	
}
