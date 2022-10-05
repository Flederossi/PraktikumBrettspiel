package com.flederossi.game;

public class Move {
    int player;
    int shiftX, shiftY;
    int x, y;

    public Move(int p, int sX, int sY, int pX, int pY){
        this.player = p;
        this.shiftX = sX;
        this.shiftY = sY;
        this.x = pX;
        this.y = pY;
    }
}
