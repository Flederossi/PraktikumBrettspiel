package com.flederossi.interfaces;

import com.flederossi.game.Move;

public interface PlayerImpl {

    Move generateNextMove(int id, int tileX, int tileY, int[][] board);
}
