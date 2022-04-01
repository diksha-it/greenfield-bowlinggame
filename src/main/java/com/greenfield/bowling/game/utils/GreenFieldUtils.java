package com.greenfield.bowling.game.utils;

import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;

public final class GreenFieldUtils {

	private GreenFieldUtils() {
		// restrict instantiation
	}

	public static final int INT_ZERO = 0;
	public static final int INT_ONE = 1;
	public static final int INT_TWO = 2;
	public static final int FRAME_SIZE_TEN = 10;
	public static final int INT_NINE = 9;
	public static final int INT_ELEVEN = 11;

	public static final String EMPTY = "";
	public static final String SINGLE_TAB = "\t";
	public static final String DOUBLE_TAB = "\t\t";

	public static final String FAILURE = "F";

	public static final String STRIKE = "X";

	public static final String SPARE = "/";
	public static final String SPACE = " ";

	public static GreenFieldBowlingPlayers getDatMap(String[] a) {
		if (a.length == GreenFieldUtils.INT_TWO) {
			return new GreenFieldBowlingPlayers(a[GreenFieldUtils.INT_ZERO], a[GreenFieldUtils.INT_ONE],
					GreenFieldUtils.FAILURE.equalsIgnoreCase(a[GreenFieldUtils.INT_ONE]) );
		}
		return new GreenFieldBowlingPlayers();
	}
}