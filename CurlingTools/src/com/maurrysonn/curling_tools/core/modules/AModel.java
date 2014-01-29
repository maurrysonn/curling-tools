package com.maurrysonn.curling_tools.core.modules;

import javax.swing.event.EventListenerList;

public abstract class AModel<T> implements IModel<T>{
	
	EventListenerList listeners = new EventListenerList();
	
	@Override
	public void addModelListener(IModelListener<T> listener) {
		listeners.add(IModelListener.class, listener);
	}
	
	@Override
	public void removeModelListener(IModelListener<T> listener) {
		listeners.remove(IModelListener.class, listener);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IModelListener<T>[] getModelListeners(){
		return listeners.getListeners(IModelListener.class);
	}
	
	
	protected void fireAdded(final T modelAdded){
		// XXX alexandre - Delete print
		System.out.println("notifyAdded() - Model="+modelAdded);
		for(final IModelListener<T> l : getModelListeners() ){
			l.added(modelAdded);
			l.listChanged();
		}
	}

	protected void fireUpdated(final T modelUpdated){
		// XXX alexandre - Delete print
		System.out.println("notifyUpdated() - Model="+modelUpdated);
		for(final IModelListener<T> l : getModelListeners()){
			l.updated(modelUpdated);
			l.listChanged();
		}
	}
	
	protected void fireRemoved(final T modelRemoved){
		// XXX alexandre - Delete print
		System.out.println("notifyRemoved() - Model="+modelRemoved);
		for(final IModelListener<T> l : getModelListeners()){
			l.removed(modelRemoved);
			l.listChanged();
		}
	}
}
