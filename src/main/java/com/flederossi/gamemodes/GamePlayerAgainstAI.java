package com.flederossi.gamemodes;

import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.interfaces.GUIImpl;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.players.AI;
import com.flederossi.players.Player;

public class GamePlayerAgainstAI extends Game {

    public GamePlayerAgainstAI(int[][] boardInit, GUIImpl ui){
        super(boardInit, ui);
        super.players = new PlayerImpl[]{new AI(), new Player()};
    }

    private Move generateAIMove(){
        return super.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, 0, 0, super.board);
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        Move move;

        move = super.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, tileX, tileY, super.board);

        if (move != null){
            if (super.board.checkLegalMove(move)){
                super.board.applyMove(move);
                super.switchCurrentPlayer();

                move = generateAIMove();
                if (move != null) {
                    super.board.applyMove(move);
                }
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
        if (super.players[super.currentPlayer - 1] instanceof AI){
            super.board.applyMove(generateAIMove());
            super.switchCurrentPlayer();
        }

        super.start();
    }
}
