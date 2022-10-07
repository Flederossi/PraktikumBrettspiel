package com.flederossi.gamemode;

import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.interfaces.GUI;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.player.AI;
import com.flederossi.player.Player;

public class GameAgainstAI extends Game {
    private final PlayerImpl[] players;

    public GameAgainstAI(int[][] boardInit, GUI ui){
        super(boardInit, ui);
        this.players = new PlayerImpl[]{new AI(), new Player()};
    }

    private Move generateAIMove(){
        return this.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, 0, 0, super.board.getBoard());
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        Move move;

        move = this.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, tileX, tileY, super.board.getBoard());

        if (move != null){
            if (super.board.checkLegalMove(move)){
                super.board.applyMove(move);

                super.switchCurrentPlayer();

                // Generate AI Move
                move = generateAIMove();
                super.board.applyMove(move);

                super.switchCurrentPlayer();
            }else{
                return -1;
            }
        }

        return 0;
    }

    @Override
    protected void start(){
        // Call the AI if the AI is black (should do the first move)
        if (this.players[super.currentPlayer - 1] instanceof AI){
            super.board.applyMove(generateAIMove());
            super.switchCurrentPlayer();
        }

        super.start();
    }
}
