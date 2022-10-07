package com.flederossi.game;

import com.flederossi.gamemodes.GameAgainstPlayer;
import com.flederossi.interfaces.GUI;

public class GameLauncher {
    public static void main(String[] args){
        int[][] board = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };

        GUI ui = new GameView();
        Game game = new GameAgainstPlayer(board, ui);
        game.start();
    }
}
