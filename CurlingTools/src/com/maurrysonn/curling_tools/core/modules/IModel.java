package com.maurrysonn.curling_tools.core.modules;

import java.util.List;

public interface IModel<T> {

	public T get(final long modelId);

	public List<T> list();

	public T add(final T modelName);

	public T update(final T model);

	public T remove(final T model);

	public void addModelListener(final ModelListener<T> listener);

	public void removeModelListener(final ModelListener<T> listener);
	
	public ModelListener<T>[] getModelListeners();
}
