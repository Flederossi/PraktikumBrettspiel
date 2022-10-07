package com.flederossi.gamemode;

import com.flederossi.game.*;
import com.flederossi.interfaces.GUI;
import com.flederossi.player.Player;
import org.eclipse.swt.events.MouseEvent;

public class GameAgainstPlayer extends Game {
    private final Player[] players;

    public GameAgainstPlayer(int[][] board, GUI ui){
        super(board, ui);

        this.players = new Player[]{new Player(), new Player()};
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        Move move;

        move = this.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, tileX, tileY, super.board.getBoard());

        if (move != null){
            if (super.board.checkLegalMove(move)){
                super.board.applyMove(move);
                super.currentPlayer = super.currentPlayer == 1 ? 2 : 1;
            }else{
                return -1;
            }
        }

        return 0;
    }
}
