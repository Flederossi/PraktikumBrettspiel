package com.flederossi.gamemodes;

import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.interfaces.GUI;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.players.AI;
import com.flederossi.players.Player;

public class GameAIAgainstAI extends Game {
    public GameAIAgainstAI(int[][] boardInit, GUI ui){
        super(boardInit, ui);
        super.players = new PlayerImpl[]{new AI(), new Player()};
    }

    private Move generateAIMove(){
        return super.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, 0, 0, super.board.getBoard());
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        super.board.applyMove(generateAIMove());
        super.switchCurrentPlayer();

        super.board.applyMove(generateAIMove());
        super.switchCurrentPlayer();

        return 0;
    }

    @Override
    protected void start(){
        super.board.applyMove(generateAIMove());
        super.switchCurrentPlayer();

        super.start();
    }
}
