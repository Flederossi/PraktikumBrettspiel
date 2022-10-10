package com.flederossi;

import com.flederossi.game.Board;
import com.flederossi.game.Game;
import com.flederossi.players.Player;
import com.flederossi.ui.GUI;
import com.flederossi.players.AI;
import com.flederossi.ui.View;

public class GameLauncher {
    public static void main(String[] args){
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };

        GUI ui = new View();
        Board board = new Board(boardInit);
        // players[0] -> Player White | players[1] -> Player Black
        Object[] players = new Object[]{new Player(), new AI()};
        Game game = new Game(board, ui, players);
        game.start();
    }
}
