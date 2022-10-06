package com.flederossi.game;

public class AI {
    private final int id;

    AI(int id){
        this.id = id;
    }

    public Move generateNextMove(int[][] board){
        Move nextMove = new Move(this.id, 0, 0, 0, 0);

        System.out.println("Called AI...");

        return nextMove;
    }
}
