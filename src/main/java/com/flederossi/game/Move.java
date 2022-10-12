package com.flederossi.game;

public class Move {
    public final Coordinate startPos;
    public final Coordinate endPos;

    public Move(Coordinate startPos, Coordinate endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }
}
