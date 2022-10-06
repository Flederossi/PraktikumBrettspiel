package com.flederossi.player;

import com.flederossi.game.Move;

public class AI {
    private final int id;

    public AI(int id){
        this.id = id;
    }

    public Move generateNextMove(int tileX, int tileY, int[][] board){
        Move nextMove = new Move(this.id, 0, 0, 0, 0);

        System.out.println("Called AI...");

        return nextMove;
    }
}
