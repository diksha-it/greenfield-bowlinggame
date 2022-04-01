package com.greenfield.bowling.game.enums;

public enum GreenFieldScoreBoardEnum {

	FRAME("Frame"), PINFALLS("Pinfalls"), SCORE("Score");

	private String value;

	private GreenFieldScoreBoardEnum(String value) {
		this.value = value;
	}
}
