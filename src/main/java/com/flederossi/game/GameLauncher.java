package com.flederossi.game;

import com.flederossi.gamemodes.GamePlayerAgainstAI;
import com.flederossi.interfaces.GUIImpl;

public class GameLauncher {
    public static void main(String[] args){
        int[][] board = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };

        GUIImpl ui = new GameView();
        Game game = new GamePlayerAgainstAI(board, ui);
        game.start();
    }
}
