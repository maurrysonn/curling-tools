package com.maurrysonn.curling_tools.core.modules;

import javax.swing.event.EventListenerList;

public abstract class AModel<T> implements IModel<T>{
	
	EventListenerList listeners = new EventListenerList();
	
	@Override
	public void addModelListener(ModelListener<T> listener) {
		listeners.add(ModelListener.class, listener);
	}
	
	@Override
	public void removeModelListener(ModelListener<T> listener) {
		listeners.remove(ModelListener.class, listener);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ModelListener<T>[] getModelListeners(){
		return listeners.getListeners(ModelListener.class);
	}
	
	
	protected void fireAdded(final T modelAdded){
		// XXX alexandre - Delete print
		System.out.println("notifyAdded() - Model="+modelAdded);
		for(final ModelListener<T> l : getModelListeners() ){
			l.added(modelAdded);
			l.listChanged();
		}
	}

	protected void fireUpdated(final T modelUpdated){
		// XXX alexandre - Delete print
		System.out.println("notifyUpdated() - Model="+modelUpdated);
		for(final ModelListener<T> l : getModelListeners()){
			l.updated(modelUpdated);
			l.listChanged();
		}
	}
	
	protected void fireRemoved(final T modelRemoved){
		// XXX alexandre - Delete print
		System.out.println("notifyRemoved() - Model="+modelRemoved);
		for(final ModelListener<T> l : getModelListeners()){
			l.removed(modelRemoved);
			l.listChanged();
		}
	}
}
