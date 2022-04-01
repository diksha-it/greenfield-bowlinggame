package com.greenfield.bowling.game.service;

import java.util.List;

import com.greenfield.bowling.game.model.GreenFieldBowlingPlayers;

public interface GreenFieldBowlingGameService {

	void startGame(List<GreenFieldBowlingPlayers> playerList);

}
