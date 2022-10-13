package com.flederossi.players;

import com.flederossi.game.Coordinate;
import com.flederossi.game.Move;

public class Player {
    public int firstX, firstY;
    public boolean firstClick;

    public Player() {
        firstClick = false;
    }

    public Move generateNextMove(int clickX, int clickY) {
        Move nextMove;

        if (firstClick) {
            nextMove = new Move(new Coordinate(firstX, firstY), new Coordinate(clickX, clickY));
        } else {
            firstX = clickX;
            firstY = clickY;
            nextMove = null;
        }

        firstClick = !firstClick;

        return nextMove;
    }
}
