package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	/*
	 * Number of teams required for this group.
	 */
	private int nbTeams;

	/*
	 * Order of this group in the Round.
	 */
	private int order;

	/*
	 * Flag represents if this group is the current group.
	 */
	private boolean current;

	/*
	 * Flag represents if this group is finished.
	 */
	private boolean finished;

	/*
	 * Group informations
	 */
	// TODO AP - Use date
	private String date;

	// TODO AP - Use time
	private String startTime;

	// TODO AP - Use time
	private String endTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbTeams() {
		return nbTeams;
	}

	public void setNbTeams(int nbTeams) {
		this.nbTeams = nbTeams;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Group #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		return strBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Group))
			return false;
		final Group objGroup = (Group) obj;
		if (this.getId() != 0 && this.getId() == objGroup.getId())
			return true;
		return false;
	}
}
