package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.maurrysonn.curling_tools.modules.tournamentModule.TournamentManager;
import com.maurrysonn.curling_tools.modules.tournamentModule.models.RoundType;

@Entity
public class Round {

	private long id;

	private Tournament tournament;
	
	private String name;

	private int rank;

	private RoundType type;

	// Groups list
	private List<Group> groups = new ArrayList<Group>();

	
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
			// cascade={CascadeType.ALL},
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

	@OneToMany(mappedBy="round", fetch=FetchType.EAGER)
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(final Group _group) {
		// Add round in group
		_group.setRound(this);
		// Persistence of group
		TournamentManager.getInstance().getGroupModel().add(_group);
		// Add group in round
		this.getGroups().add(_group);
	}

	public void updateGroup(Group _group) {
		if(_group.getRound().equals(this) && getGroups().contains(_group)) {
			// Update Group
			final Group groupUpdated = TournamentManager.getInstance().getGroupModel().update(_group);
			// Update group list
			this.getGroups().remove(_group);
			this.getGroups().add(groupUpdated);
		}
	}

	public void removeGroup(final Group _group) {
		// Check if group is in round
		if(_group.getRound().equals(this) && getGroups().contains(_group)) {
			// Remove group
			TournamentManager.getInstance().getGroupModel().remove(_group);
			// Remove group in round
			this.getGroups().remove(_group);
		}
	}

	public void updateRound(final Group _group) {
		// Check if group is in round
		if(_group.getRound().equals(this) && getGroups().contains(_group)) {
			// Update group
			final Group groupUpdated = TournamentManager.getInstance().getGroupModel().update(_group);
			// Update groups list
			this.getGroups().remove(_group);
			this.getGroups().add(groupUpdated);
		}
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
		strBuilder.append(" - Rank=");
		strBuilder.append(getRank());
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
