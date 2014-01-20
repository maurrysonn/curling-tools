package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

@Entity
public class Round {

	private long id;

	private Tournament tournament;
	
	private String name;

	private int rank;

	private RoundType type;

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

	@ManyToOne(
			cascade={CascadeType.ALL},
			fetch=FetchType.EAGER,
			optional=false)
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Enumerated(EnumType.STRING)
	public RoundType getType() {
		return type;
	}

	public void setType(RoundType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Round #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		strBuilder.append(" - ");
		strBuilder.append(getType());
		return strBuilder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Round))
			return false;
		final Round objRound = (Round) obj;
		if (this.getId() != 0 && this.getId() == objRound.getId())
			return true;
		return false;
	}

}
