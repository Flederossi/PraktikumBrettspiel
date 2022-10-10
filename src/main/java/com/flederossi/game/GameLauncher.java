package com.flederossi.game;

import com.flederossi.interfaces.GUI;
import com.flederossi.players.AI;
import com.flederossi.players.Player;

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
        // players[0] -> Player White | players[1] -> Player Black
        Object[] players = new Object[]{new Player(), new AI()};
        Game game = new Game(board, ui, players);
        game.start();
    }
}
