package com.flederossi.game;

public class GameLauncher {
    public static void main(String[] args){
        int[][] board = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };

        GUI ui = new GameUI();
        Game game = new Game(board, ui);
        game.start();
    }
}
