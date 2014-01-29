package com.maurrysonn.curling_tools.core.modules;

import java.util.ArrayList;
import java.util.List;

public abstract class AModule {
	private int id;
	private String name;
	private String verboseName;
	private List<AView> viewsList;
	private List<IAction> actionsList;
	private List<AModel<?>> modelsList;
	
	/**
	 * @deprecated will be removed in futur release!
	 * 
	 * @param _id
	 * @param _name
	 * @param _verboseName
	 * @param _viewsList
	 * @param _actionsList
	 * @param _modelsList
	 */
	public AModule(final int _id, final String _name, final String _verboseName, final List<AView> _viewsList, final List<IAction> _actionsList, final List<AModel<?>> _modelsList) {
		id = _id;
		name = _name;
		verboseName = _verboseName;
		viewsList = _viewsList;
		actionsList = _actionsList;
		modelsList = _modelsList;
	}
	/**
	 * @deprecated remove useless actionsList 
	 * 
	 * @param _id
	 * @param _name
	 * @param _verboseName
	 * @param _viewsList
	 * @param _actionsList
	 */
	public AModule(final int _id, final String _name, final String _verboseName, final List<AView> _viewsList, final List<IAction> _actionsList) {
		id = _id;
		name = _name;
		verboseName = _verboseName;
		viewsList = _viewsList;
		actionsList = _actionsList;
		modelsList = new ArrayList<AModel<?>>();
	}
	
	public AModule(final int _id, final String _name, final String _verboseName, final List<AView> _viewsList) {
		id = _id;
		name = _name;
		verboseName = _verboseName;
		viewsList = _viewsList;
		actionsList = new ArrayList<IAction>();
		modelsList = new ArrayList<AModel<?>>();
	}
	
	public AModule(final int _id, final String _name, final String _verboseName) {
		id = _id;
		name = _name;
		verboseName = _verboseName;
		viewsList = new ArrayList<AView>();
		actionsList = new ArrayList<IAction>();
		modelsList = new ArrayList<AModel<?>>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<AView> getViewsList() {
		return viewsList;
	}

	public void setViewsList(final List<AView> viewsList) {
		this.viewsList = viewsList;
	}
	
	public void addView(final AView view){
		this.viewsList.add(view);
	}

	public List<IAction> getActionsList() {
		return actionsList;
	}

	public void setActionsList(final List<IAction> actionsList) {
		this.actionsList = actionsList;
	}

	public String getVerboseName() {
		return verboseName;
	}

	public void setVerboseName(final String verboseName) {
		this.verboseName = verboseName;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public List<AModel<?>> getModelsList() {
		return modelsList;
	}

	public void setModelsList(List<AModel<?>> modelsList) {
		this.modelsList = modelsList;
	}
}
