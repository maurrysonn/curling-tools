package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Match {
	
	private long id;

	private Group group;
	
	private Team team1;
	
	private Team team2;
	
	private int sheet;
	
	private boolean finished;
	
	// Cache of scores ?
	// private int scoreTeam1;
	// private int scoreTeam2;

	private Set<EndResult> endResults = new HashSet<EndResult>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(
			fetch=FetchType.EAGER,
			optional=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne(
			fetch=FetchType.EAGER,
			optional=true)
	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	@ManyToOne(
			fetch=FetchType.EAGER,
			optional=true)
	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public int getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@OneToMany(mappedBy="match")
	public Set<EndResult> getEndResults() {
		return endResults;
	}

	public void setEndResults(Set<EndResult> endResults) {
		this.endResults = endResults;
	}
}
