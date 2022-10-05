package com.flederossi.players;

import com.flederossi.game.Move;

public class AI {

    int id;

    AI(int id){
        this.id = id;
    }

    Move generateNextMove(int[][] board){
        Move nextMove = new Move(this.id, 0, 0, 0, 0);
        return nextMove;
    }
}
