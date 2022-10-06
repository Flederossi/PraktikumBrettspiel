package com.flederossi.game;

public class Player {
    private final int id;

    protected int firstX, firstY;
    private boolean firstClick;

    protected Player(int id){
        this.id = id;
        this.firstClick = false;
    }

    private int[] convertCoordsToShift(int x1, int y1, int x2, int y2){
        return new int[]{x2 - x1, y2 - y1};
    }

    protected Move generateNextMove(int tileX, int tileY, int[][] board){
        Move nextMove;

        if (firstClick) {
            int sX = convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[0];
            int sY = convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[1];
            nextMove = new Move(this.id, sX, sY, this.firstX, this.firstY);
        }else{
            this.firstX = tileX;
            this.firstY = tileY;
            nextMove = null;
        }

        this.firstClick = !this.firstClick;

        return nextMove;
    }
}
