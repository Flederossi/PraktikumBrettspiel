package com.flederossi.players;

import com.flederossi.game.Move;
import com.flederossi.interfaces.PlayerImpl;

public class AI implements PlayerImpl {
    // TODO Make the ai smarter
    @Override
    public Move generateNextMove(int id, int tileX, int tileY, int[][] board){
        Move nextMove = new Move(id, 0, 0, 0, 0);

        System.out.println("Called AI...");

        return nextMove;
    }
}
