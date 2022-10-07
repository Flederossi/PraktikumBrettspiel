package com.flederossi.game;

public class Board {
    private final int[][] board;

    public Board(int[][] boardInit) {
        this.board = boardInit;
    }

    private int getPlayerTargetField(Move move) {
        return this.board[move.y + move.shiftY][move.x + move.shiftX];
    }

    public boolean checkLegalMove(Move move) {
        if (move.x + move.shiftX >= 0 && move.x + move.shiftX <= 4 && move.y + move.shiftY >= 0 && move.y + move.shiftY <= 4) {
            // Check if move is diagonal
            if (move.shiftX == 0 ^ move.shiftY == 0) {
                // Check if move is from the right player to the right field
                return this.getPlayerTargetField(move) == move.player - 1 && (this.board[move.y][move.x] == move.player) && Math.abs(move.shiftX) < 2 && Math.abs(move.shiftY) < 2;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    public void applyMove(Move move) {
        this.board[move.y][move.x] = 0;
        this.board[move.y + move.shiftY][move.x + move.shiftX] = move.player;
    }

    public int[][] getBoard() {
        return this.board;
    }
}
