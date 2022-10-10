package com.flederossi.players;

import com.flederossi.game.Board;
import com.flederossi.game.Coordinate;
import com.flederossi.game.Move;

public class PlayerImpl implements com.flederossi.interfaces.Player {
    public int firstX, firstY;
    private boolean firstClick;

    public PlayerImpl(){
        this.firstClick = false;
    }

    @Override
    public Move generateNextMove(int id, int tileX, int tileY, Board board){
        Move nextMove;

        if (firstClick) {
            nextMove = new Move(new Coordinate(this.firstX, this.firstY), new Coordinate(tileX, tileY));
        }else{
            this.firstX = tileX;
            this.firstY = tileY;
            nextMove = null;
        }

        this.firstClick = !this.firstClick;

        return nextMove;
    }
}
