package com.maurrysonn.curling_tools.core.modules;

import java.util.EventListener;

public interface ModelListener<T> extends EventListener{
	public void listChanged();
	public void added(final T model);
	public void updated(final T model);
	public void removed(final T model);
}
