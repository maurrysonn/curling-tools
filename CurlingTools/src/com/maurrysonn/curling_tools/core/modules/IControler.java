package com.maurrysonn.curling_tools.core.modules;

import java.util.List;

public interface IControler<T> {
	
	public List<T> getList();

	public void update(T model);

	public void add(T model);

	public void delete(T model);

}
