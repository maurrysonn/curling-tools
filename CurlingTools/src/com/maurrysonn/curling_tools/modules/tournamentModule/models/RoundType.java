package com.maurrysonn.curling_tools.modules.tournamentModule.models;

public enum RoundType {
	GROUP("Group"),
	RANKING("Ranking"),
	FINAL("Final");
	
	private String name;
	
	private RoundType(final String _name) {
		name = _name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
