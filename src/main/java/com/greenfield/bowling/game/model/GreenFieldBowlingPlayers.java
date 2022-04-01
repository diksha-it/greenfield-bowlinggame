package com.greenfield.bowling.game.model;

public class GreenFieldBowlingPlayers implements Comparable<GreenFieldBowlingPlayers> {

	public String name;

	public String score;

	public GreenFieldBowlingPlayers() {
		super();
	}

	public GreenFieldBowlingPlayers(String name, String score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public int compareTo(GreenFieldBowlingPlayers o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "Players [name=" + name + ", score=" + score + "]";
	}

}
