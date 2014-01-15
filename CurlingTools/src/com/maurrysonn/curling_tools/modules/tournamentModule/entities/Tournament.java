package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/*
	 * Tournament data :
	 */
	private String name;

	// TODO AP - Use date
	private String startDate;
	
	// TODO AP - Use date
	private String endDate;
	
	private String club;
	
	private String rink;
	
	/*
	 * Accessors
	 */
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getRink() {
		return rink;
	}

	public void setRink(String rink) {
		this.rink = rink;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Tournament #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		return strBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Tournament)) return false;
		final Tournament objTournament = (Tournament) obj;
		if(this.getId() != 0 && this.getId() == objTournament.getId()) return true;
		return false;
	}
	
}
