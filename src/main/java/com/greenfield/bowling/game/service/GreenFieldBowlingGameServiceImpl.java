package com.greenfield.bowling.game.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.greenfield.bowling.game.enums.GreenFieldScoreBoardEnum;
import com.greenfield.bowling.game.model.GreenFieldBowlingGame;
import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;
import com.greenfield.bowling.game.utils.GreenFieldUtils;

public class GreenFieldBowlingGameServiceImpl implements GreenFieldBowlingGameService {

	@Override
	public void startGame(List<GreenFieldBowlingPlayers> playerList) {

//		Optional.ofNullable(playerList).orElseGet(Collections::emptyList).stream()
//				.forEach(e -> System.out.println("Key : " + e.getName() + " value : " + e.getScore()));

		Map<String, List<GreenFieldBowlingPlayers>> map = playerList.stream()
				.collect(Collectors.groupingBy(GreenFieldBowlingPlayers::getName));

		printScoreBoard(map);

	}

	private void printScoreBoard(Map<String, List<GreenFieldBowlingPlayers>> map) {

		// print frame No
		printFrame();

		map.entrySet().stream().forEach(players -> {

			// print player name
			System.out.println(players.getKey());

			// print pinfalls & score
			calculateScoreAndPinfalls(players.getValue());

		});

	}

	private void printFrame() {
		System.out.print(GreenFieldScoreBoardEnum.FRAME.name() + GreenFieldUtils.DOUBLE_TAB);

		IntStream.range(GreenFieldUtils.INT_ZERO, GreenFieldUtils.FRAME_SIZE_TEN).forEach(frameNo -> {

			System.out.print((frameNo + GreenFieldUtils.INT_ONE) + GreenFieldUtils.DOUBLE_TAB);
		});

		System.out.println(GreenFieldUtils.EMPTY);
	}

	private void calculateScoreAndPinfalls(List<GreenFieldBowlingPlayers> playerList) {

		GreenFieldBowlingGame game = new GreenFieldBowlingGame();

		playerList.stream().forEach(p -> {

			String roll = GreenFieldUtils.FAILURE.equalsIgnoreCase(p.getScore()) ? "0" : p.getScore();

			game.roll(Integer.parseInt(roll));

		});

		printPinfalls(game);

		printScore(game);

	}

	private void printPinfalls(GreenFieldBowlingGame game) {
		System.out.print(GreenFieldScoreBoardEnum.PINFALLS.name() + GreenFieldUtils.SINGLE_TAB);

		for (int index = GreenFieldUtils.INT_ZERO; index < game.frames.length; index++) {

			String first = game.frames[index].getFirstRoll() == GreenFieldUtils.FRAME_SIZE_TEN
					&& index != GreenFieldUtils.INT_NINE ? GreenFieldUtils.EMPTY
							: game.frames[index].getFirstRoll() + GreenFieldUtils.EMPTY;

			String second = GreenFieldUtils.EMPTY;

			if (game.frames[index].isSpare())
				second = GreenFieldUtils.SPARE;
			else if (game.frames[index].isStrike())
				second = GreenFieldUtils.STRIKE;
			else
				second = game.frames[index].getSecondRoll() == GreenFieldUtils.FRAME_SIZE_TEN ? GreenFieldUtils.EMPTY
						: game.frames[index].getSecondRoll() + GreenFieldUtils.EMPTY;

			if (index < GreenFieldUtils.INT_ELEVEN)
				System.out.print(first + GreenFieldUtils.SINGLE_TAB + second + GreenFieldUtils.SINGLE_TAB);

		}

		System.out.println(GreenFieldUtils.EMPTY);
	}

	private void printScore(GreenFieldBowlingGame game) {
		System.out.print(GreenFieldScoreBoardEnum.SCORE.name() + GreenFieldUtils.DOUBLE_TAB);

		// printing Score board
		for (int index = GreenFieldUtils.INT_ZERO; index < game.frames.length; index++) {
			if (index < GreenFieldUtils.FRAME_SIZE_TEN)
				System.out.print(game.frames[index].getPoints() + GreenFieldUtils.DOUBLE_TAB);

		}
		System.out.println(GreenFieldUtils.EMPTY);
	}

}
