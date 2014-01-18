package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
public class Tournament {

	private long id;

	/*
	 * Tournament data :
	 */
	private String name;
	// Dates
	private Date startDate;
	private Date endDate;
	// Name of club
	private String club;
	// Place of tournament
	private String rink;

	/*
	 * Accessors
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
		if (this == obj)
			return true;
		if (!(obj instanceof Tournament))
			return false;
		final Tournament objTournament = (Tournament) obj;
		if (this.getId() != 0 && this.getId() == objTournament.getId())
			return true;
		return false;
	}

}
