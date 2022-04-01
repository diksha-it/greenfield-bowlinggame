package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.greenfield.bowling.game.GreenFieldBowlingGameMain;
import com.greenfield.bowling.game.model.GreenFieldBowlingGame;
import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;
import com.greenfield.bowling.game.service.GreenFieldBowlingGameServiceImpl;
import com.greenfield.bowling.game.utils.GreenFieldUtils;

@RunWith(JUnit4.class)
public class BowlingGameTest {

	@Test(expected = Exception.class)
	public void testScoreWithWrongFile() throws IOException {

		GreenFieldBowlingGameServiceImpl tenPinsBowlingGameService = new GreenFieldBowlingGameServiceImpl();
		tenPinsBowlingGameService.startGame("test");

	}

	@Test
	public void testPositive() throws IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("bowling-game.txt").getFile());

		List<GreenFieldBowlingPlayers> playerList = new ArrayList<>();

		playerList = Files.lines(file.toPath()).map(s -> GreenFieldUtils.getDatMap(s.split(GreenFieldUtils.SPACE)))
				.collect(Collectors.toList());

		GreenFieldBowlingGameServiceImpl tenPinsBowlingGameService = new GreenFieldBowlingGameServiceImpl();
		String status = tenPinsBowlingGameService.startPalyingGame(playerList);

		Assert.assertEquals("SUCCESS", status);

	}

	@Test
	public void testMapConversion() throws IOException {

		GreenFieldBowlingPlayers players = GreenFieldUtils.getDatMap(new String[] {});

		Assert.assertEquals(null, players.getName());

	}

	@Test
	public void testMainMethodNegative() throws IOException {

		GreenFieldBowlingGameMain.main(new String[] {});

	}

	@Test
	public void testMainMethodPositive() throws IOException {

		GreenFieldBowlingGameMain.main(new String[] { "bowling-game.txt" });

	}
	
	@Test
	public void testInvalidPins() throws IOException {

		GreenFieldBowlingGame game=new GreenFieldBowlingGame();
		
		game.roll(900);
		

	}

}