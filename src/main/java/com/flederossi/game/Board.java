package com.flederossi.game;

public class Board {
    public int[][] board;

    public Board(int[][] boardInit) {
        this.board = boardInit;
    }

    private int getPlayerTargetField(Move move) {
        return this.board[move.endPos.y][move.endPos.x];
    }

    public boolean checkLegalMove(Move move, int player) {
        if (move.endPos.x >= 0 && move.endPos.x <= 4 && move.endPos.y >= 0 && move.endPos.y <= 4) {
            // Check if move is diagonal
            if (move.endPos.x - move.startPos.x == 0 ^ move.endPos.y - move.startPos.y == 0) {
                // Check if move is from the right player to the right field
                return this.getPlayerTargetField(move) == player - 1 && (this.board[move.startPos.y][move.startPos.x] == player) && Math.abs(move.endPos.x - move.startPos.x) < 2 && Math.abs(move.endPos.y - move.startPos.y) < 2;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void applyMove(Move move) {
        this.board[move.endPos.y][move.endPos.x] = this.board[move.startPos.y][move.startPos.x];
        this.board[move.startPos.y][move.startPos.x] = 0;
    }

    public void setBoard(int[][] newBoard) {
        this.board = newBoard;
    }

    public int[][] getBoard() {
        return this.board;
    }
}
