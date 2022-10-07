package com.flederossi.game;

import com.flederossi.gamemodes.GamePlayerAgainstPlayer;
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
        Game game = new GamePlayerAgainstPlayer(board, ui);
        game.start();
    }
}
