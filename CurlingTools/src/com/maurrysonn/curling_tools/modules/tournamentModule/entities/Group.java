package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="GROUP_TABLE")
public class Group {

	private long id;

	private String name;

	private Round round;
	
	/*
	 * Number of teams required for this group.
	 */
	private int nbTeams;

	/*
	 * Order of this group in the Round.
	 */
	private int rank;

	/*
	 * Flag represents if this group is the current group.
	 */
	private boolean current;

	/*
	 * Flag represents if this group is finished.
	 */
	private boolean finished;

	/*
	 * Date and Time informations
	 */
	private Date startTime;
	private Date endTime;

	private Set<Match> matches = new HashSet<Match>();
	
	
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

	@ManyToOne(
			fetch=FetchType.EAGER,
			optional=false)
	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public int getNbTeams() {
		return nbTeams;
	}

	public void setNbTeams(int nbTeams) {
		this.nbTeams = nbTeams;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}
	
	@Transient
	public String getVerboseStartTime() {
		final Date d = getStartTime();
		if(d != null) {
			// TODO AP - Date management
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
			return sdf.format(d);
		} else {
			return "";
		}
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@OneToMany(mappedBy="group")
	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Group #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		strBuilder.append(" - Start= ");
		strBuilder.append(getVerboseStartTime());
		strBuilder.append(" - Rank= ");
		strBuilder.append(getRank());
		strBuilder.append(" - NbTeams= ");
		strBuilder.append(getNbTeams());
		strBuilder.append(" - Round= ");
		strBuilder.append(getRound());
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
