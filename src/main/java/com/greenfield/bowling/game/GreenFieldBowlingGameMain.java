package com.greenfield.bowling.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;
import com.greenfield.bowling.game.service.GreenFieldBowlingGameServiceImpl;
import com.greenfield.bowling.game.utils.GreenFieldUtils;

public class GreenFieldBowlingGameMain {

	/*
	 * starting point to check score board of users args[0] is mandatory
	 */

	public static void main(String[] args) {

		if (args.length == GreenFieldUtils.INT_ONE) {

			System.out.println("Input Parameter For File Is:" + args[0]);

			List<GreenFieldBowlingPlayers> playerList = new ArrayList<>();

			try {
				playerList = Files.lines(new File(args[GreenFieldUtils.INT_ZERO]).toPath())
						.map(s -> getDatMap(s.split(GreenFieldUtils.SPACE))).collect(Collectors.toList());

				GreenFieldBowlingGameServiceImpl tenPinsBowlingGameService = new GreenFieldBowlingGameServiceImpl();
				tenPinsBowlingGameService.startGame(playerList);

			} catch (IOException e) {
				System.out.println("Exception " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			}

		} else {
			System.out.println("Required exact one param as filePath including filename.txt");
		}

	}

	public static GreenFieldBowlingPlayers getDatMap(String[] a) {
		if (a.length == GreenFieldUtils.INT_TWO) {
			return new GreenFieldBowlingPlayers(a[GreenFieldUtils.INT_ZERO], a[GreenFieldUtils.INT_ONE]);
		}
		return new GreenFieldBowlingPlayers();
	}

}
