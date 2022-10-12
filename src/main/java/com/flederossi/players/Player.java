package com.flederossi.players;

import com.flederossi.game.Coordinate;
import com.flederossi.game.Move;

public class Player {
    public int firstX, firstY;
    public boolean firstClick;

    public Player() {
        this.firstClick = false;
    }

    public Move generateNextMove(int clickX, int clickY) {
        Move nextMove;

        if (firstClick) {
            nextMove = new Move(new Coordinate(this.firstX, this.firstY), new Coordinate(clickX, clickY));
        } else {
            this.firstX = clickX;
            this.firstY = clickY;
            nextMove = null;
        }

        this.firstClick = !this.firstClick;

        return nextMove;
    }
}
