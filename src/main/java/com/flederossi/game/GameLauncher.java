package com.flederossi.game;

import com.flederossi.interfaces.GUI;
import com.flederossi.interfaces.Player;
import com.flederossi.players.AIImpl;

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
        Player[] players = new Player[]{new AIImpl(), new AIImpl()};
        Game game = new Game(board, ui, players);
        game.start();
    }
}
