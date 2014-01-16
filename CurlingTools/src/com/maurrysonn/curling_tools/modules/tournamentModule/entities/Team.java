package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

	private long id;
	
	/*
	 * Team data
	 */
	private String name;
	
	/*
	 * Club data
	 */
	private String club;
	
	/*
	 * Players data
	 */
	private String skip;
	private String leadPlayer;
	private String secondPlayer;
	private String thirdPlayer;
	
	/*
	 * Accessors
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getSkip() {
		return skip;
	}

	public void setSkip(String skip) {
		this.skip = skip;
	}

	public String getLeadPlayer() {
		return leadPlayer;
	}

	public void setLeadPlayer(String leadPlayer) {
		this.leadPlayer = leadPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(String secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public String getThirdPlayer() {
		return thirdPlayer;
	}

	public void setThirdPlayer(String thirdPlayer) {
		this.thirdPlayer = thirdPlayer;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Team #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		strBuilder.append(" ( ");
		strBuilder.append(getSkip());
		strBuilder.append(" )");
		strBuilder.append(" - ");
		strBuilder.append(getClub());
		return strBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Team)) return false;
		final Team objTeam = (Team) obj;
		if(this.getId() != 0 && this.getId() == objTeam.getId()) return true;
		return false;
	}
}
