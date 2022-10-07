package com.flederossi.gamemodes;

import com.flederossi.game.*;
import com.flederossi.interfaces.GUI;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.players.Player;

public class GamePlayerAgainstPlayer extends Game {
    public GamePlayerAgainstPlayer(int[][] board, GUI ui){
        super(board, ui);
        super.players = new PlayerImpl[]{new Player(), new Player()};
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        Move move;

        move = super.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, tileX, tileY, super.board.getBoard());

        if (move != null){
            if (super.board.checkLegalMove(move)){
                super.board.applyMove(move);
                super.switchCurrentPlayer();
            }else{
                return -1;
            }
        }

        return 0;
    }
}
