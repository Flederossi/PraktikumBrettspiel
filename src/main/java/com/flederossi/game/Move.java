package com.flederossi.game;

public class Move {
    protected final Coordinate startPos;
    protected final Coordinate endPos;

    public Move(Coordinate startPos, Coordinate endPos){
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public String debugMove(){
        return "Move from (" + this.startPos.x + " | " + this.startPos.y + ") to (" + this.endPos.x + " | " + this.endPos.y + ")";
    }
}
