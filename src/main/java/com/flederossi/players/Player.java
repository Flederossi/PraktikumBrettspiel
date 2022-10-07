package com.flederossi.players;

import com.flederossi.game.Board;
import com.flederossi.game.Move;
import com.flederossi.interfaces.PlayerImpl;

public class Player implements PlayerImpl {
    public int firstX, firstY;
    private boolean firstClick;

    public Player(){
        this.firstClick = false;
    }

    @Override
    public Move generateNextMove(int id, int tileX, int tileY, Board board){
        Move nextMove;

        if (firstClick) {
            int sX = this.convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[0];
            int sY = this.convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[1];
            nextMove = new Move(id, sX, sY, this.firstX, this.firstY);
        }else{
            this.firstX = tileX;
            this.firstY = tileY;
            nextMove = null;
        }

        this.firstClick = !this.firstClick;

        return nextMove;
    }
}
