package com.maurrysonn.curling_tools.modules.tournamentModule.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Round {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	// TODO AP - Round Tournament FK
	
	private String name;
	
	private int order;

	// TODO AP - Type of round
	
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Round #");
		strBuilder.append(getId());
		strBuilder.append(" : ");
		strBuilder.append(getName());
		return strBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Round)) return false;
		final Round objRound = (Round) obj;
		if(this.getId() != 0 && this.getId() == objRound.getId()) return true;
		return false;
	}
}
