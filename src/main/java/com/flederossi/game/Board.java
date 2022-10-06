package com.flederossi.game;

public class Board {
    private int[][] board;

    public Board(int[][] boardInit){
        this.board = boardInit;
    }

    private int getPlayerTargetField(Move move){
        return this.board[move.y + move.shiftY][move.x + move.shiftX];
    }

    public boolean checkLegalMove(Move move){
        // Check if move is diagonal
        if (move.shiftX == 0 ^ move.shiftY == 0) {
            // Check if move is from the right player to the right field
            return ((move.player == 1 && this.getPlayerTargetField(move) == 0) || (move.player == 2 && this.getPlayerTargetField(move) == 1)) && (this.board[move.y][move.x] == move.player) && Math.abs(move.shiftX) < 2 && Math.abs(move.shiftY) < 2;
        }else{
            return false;
        }
    }

    protected void applyMove(Move move){
        this.board[move.y][move.x] = 0;
        this.board[move.y + move.shiftY][move.x + move.shiftX] = move.player;
    }

    public void setBoard(int[][] newBoard){
        this.board = newBoard;
    }

    protected int[][] getBoard(){
        return this.board;
    }
}
