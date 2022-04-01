package com.greenfield.bowling.game;

import com.greenfield.bowling.game.service.GreenFieldBowlingGameServiceImpl;
import com.greenfield.bowling.game.utils.GreenFieldUtils;

public class GreenFieldBowlingGameMain {

	/*
	 * starting point to check score board of users args[0] is mandatory
	 */

	public static void main(String[] args) {

		if (args.length == GreenFieldUtils.INT_ONE) {

			System.out.println("Input Parameter For File Is:" + args[0]);

			GreenFieldBowlingGameServiceImpl tenPinsBowlingGameService = new GreenFieldBowlingGameServiceImpl();
			try {
				tenPinsBowlingGameService.startGame(args[GreenFieldUtils.INT_ZERO]);
			} catch (Exception e) {
				System.out.println("Exception" + e.getMessage());

			}

		} else {
			System.out.println("Required exact one param as filePath including filename.txt");
		}

	}

}
