package com.greenfield.bowling.game.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.greenfield.bowling.game.enums.GreenFieldScoreBoardEnum;
import com.greenfield.bowling.game.model.GreenFieldBowlingGame;
import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;
import com.greenfield.bowling.game.utils.GreenFieldUtils;

public class GreenFieldBowlingGameServiceImpl implements GreenFieldBowlingGameService {

	@Override
	public void startGame(String filePath) throws IOException {
		List<GreenFieldBowlingPlayers> playerList = new ArrayList<>();

		playerList = Files.lines(new File(filePath).toPath())
				.map(s -> GreenFieldUtils.getDatMap(s.split(GreenFieldUtils.SPACE))).collect(Collectors.toList());

		startPalyingGame(playerList);
	}

	public String startPalyingGame(List<GreenFieldBowlingPlayers> playerList) {

		Map<String, List<GreenFieldBowlingPlayers>> map = playerList.stream()
				.collect(Collectors.groupingBy(GreenFieldBowlingPlayers::getName));

		Map<String, List<GreenFieldBowlingPlayers>> treeMap = getTreeMap(map);

		printScoreBoard(treeMap);
		
		return "SUCCESS";

	}

	public static <K, V> Map<K, V> getTreeMap(Map<K, V> hashMap) {
		return hashMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> newValue, TreeMap::new));
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

		printPinfalls(game,playerList);

		printScore(game);

	}

	private void printPinfalls(GreenFieldBowlingGame game,List<GreenFieldBowlingPlayers> playerList) {
		System.out.print(GreenFieldScoreBoardEnum.PINFALLS.name() + GreenFieldUtils.SINGLE_TAB);

		for (int index = GreenFieldUtils.INT_ZERO; index < game.frames.length; index++) {

			StringBuilder first = new StringBuilder(GreenFieldUtils.EMPTY);
			if (index != GreenFieldUtils.INT_NINE) {
				if (game.frames[index].getFirstRoll() != GreenFieldUtils.FRAME_SIZE_TEN) {
					
					first.append(game.frames[index].getFirstRoll());
				}
				first.append(GreenFieldUtils.SINGLE_TAB);
			}

			StringBuilder second = new StringBuilder(GreenFieldUtils.EMPTY);

			if (game.frames[index].isSpare())
				second.append(GreenFieldUtils.SPARE);
			else if (game.frames[index].isStrike())
				second.append(GreenFieldUtils.STRIKE);
			else {
				if (game.frames[index].getSecondRoll() != GreenFieldUtils.FRAME_SIZE_TEN)
					second.append(game.frames[index].getSecondRoll());
			}
			if (index < GreenFieldUtils.INT_ELEVEN)
				System.out.print(first.toString() + second + GreenFieldUtils.SINGLE_TAB);
			

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
