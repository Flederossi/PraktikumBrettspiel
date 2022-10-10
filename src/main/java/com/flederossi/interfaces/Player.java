package com.flederossi.interfaces;

import com.flederossi.game.Board;
import com.flederossi.game.Move;

public interface Player {

    default int[] convertCoordsToShift(int x1, int y1, int x2, int y2){
        return new int[]{x2 - x1, y2 - y1};
    }

    Move generateNextMove(int id, int tileX, int tileY, Board board);
}
