package com.flederossi.game;

import java.util.Arrays;

public final class Board {
    // TODO Make board one dimensional
    private final int[][] board;

    public Board(int[][] boardInit) {
        board = new int[boardInit.length][];

        for (int i = 0; i < boardInit.length; i++){
            board[i] = Arrays.copyOf(boardInit[i], boardInit[i].length);
        }
    }

    public int getPlayer(Coordinate coordinate){
        return board[coordinate.y][coordinate.x];
    }

    public Board setPlayer(Coordinate coordinate, int player){
        Board boardCopy = new Board(board);
        boardCopy.board[coordinate.y][coordinate.x] = player;
        return boardCopy;
    }

    public int[][] getBoard(){
        return board;
    }

    public void setBoard(int[][] boardInit){
        for (int i = 0; i < boardInit.length; i++){
            board[i] = Arrays.copyOf(boardInit[i], boardInit[i].length);
        }
    }
}
