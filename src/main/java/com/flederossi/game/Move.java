package com.flederossi.game;

public class Move {
    protected final int player;
    protected final int shiftX, shiftY;
    protected final int x, y;

    public Move(int p, int sX, int sY, int pX, int pY){
        this.player = p;
        this.shiftX = sX;
        this.shiftY = sY;
        this.x = pX;
        this.y = pY;
    }

    public String debugMove(){
        return "Move by " + this.player + " from (" + this.x + " | " + this.y + ") with (" + this.shiftX + " | " + this.shiftY + ")";
    }
}
