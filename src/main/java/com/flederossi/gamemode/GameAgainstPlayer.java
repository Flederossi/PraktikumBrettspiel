package com.flederossi.gamemode;

import com.flederossi.game.*;
import com.flederossi.interfaces.GUI;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.player.Player;

public class GameAgainstPlayer extends Game {
    private final PlayerImpl[] players;

    public GameAgainstPlayer(int[][] board, GUI ui){
        super(board, ui);
        this.players = new PlayerImpl[]{new Player(), new Player()};
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        Move move;

        move = this.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, tileX, tileY, super.board.getBoard());

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
