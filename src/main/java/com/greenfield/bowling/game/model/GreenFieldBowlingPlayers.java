package com.greenfield.bowling.game.model;

public class GreenFieldBowlingPlayers {

	public String name;

	public String score;

	public boolean isF;

	public GreenFieldBowlingPlayers() {
		super();
	}

	public GreenFieldBowlingPlayers(String name, String score, boolean isF) {
		super();
		this.name = name;
		this.score = score;
		this.isF = isF;
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

	public boolean isF() {
		return isF;
	}

	public void setF(boolean isF) {
		this.isF = isF;
	}

	@Override
	public String toString() {
		return "GreenFieldBowlingPlayers [name=" + name + ", score=" + score + ", isF=" + isF + "]";
	}

}
