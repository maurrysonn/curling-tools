package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.maurrysonn.curling_tools.core.utils.PersistenceUtils;

@Entity
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

	// Rounds
	private Set<Round> rounds = new HashSet<Round>();
	
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
	
	@Transient
	public String getVerboseStartDate() {
		final Date d = getStartDate();
		if(d != null) {
			// TODO AP - Date management
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);
		} else {
			return "";
		}
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	@Transient
	public String getVerboseEndDate() {
		final Date d = getEndDate();
		if(d != null) {
			// TODO AP - Date management
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);			
		} else {
			return "";
		}
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
	
	public void addRound(final Round _round) {
		// Get EntityManager
		EntityManager em = PersistenceUtils.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// Add tournament in round
		_round.setTournament(this);
		// Add round in tournament
		this.getRounds().add(_round);
		// Persistence of round
		em.persist(_round);
		// Merge of tournament
		em.merge(this);
		// Stop Transaction and close EntityManager
		tx.commit();
		em.close();
	}
	
	@OneToMany(mappedBy="tournament")
	public Set<Round> getRounds() {
		return rounds;
	}

	public void setRounds(Set<Round> rounds) {
		this.rounds = rounds;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Tournament #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		strBuilder.append(" - ");
		strBuilder.append(dateFormat.format(getStartDate()));
		strBuilder.append(" | ");
		strBuilder.append(dateFormat.format(getEndDate()));
		strBuilder.append(" - ");
		strBuilder.append(getClub());
		strBuilder.append(" | ");
		strBuilder.append(getRink());
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

	public void printRounds() {
		// XXX AP - Delete print
		System.out.println("== List of Rounds");
		for (final Round round : getRounds()) {
			// XXX AP - Delete print
			System.out.println(round);
		}
	}
	
}
