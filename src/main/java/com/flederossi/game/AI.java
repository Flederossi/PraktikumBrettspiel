package com.flederossi.game;

public class AI {
    private final int id;

    protected AI(int id){
        this.id = id;
    }

    protected Move generateNextMove(int tileX, int tileY, int[][] board){
        Move nextMove = new Move(this.id, 0, 0, 0, 0);

        System.out.println("Called AI...");

        return nextMove;
    }
}
