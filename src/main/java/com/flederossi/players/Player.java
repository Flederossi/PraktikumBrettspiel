package com.flederossi.players;

import com.flederossi.game.Move;

public class Player {

    int id;

    Player(int id){
        this.id = id;
    }

    Move generateNextMove(){
        Move nextMove = new Move(this.id, 0, 0, 0, 0);
        return nextMove;
    }
}
