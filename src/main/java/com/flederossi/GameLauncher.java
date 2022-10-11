package com.flederossi;

import com.flederossi.game.Board;
import com.flederossi.game.Game;
import com.flederossi.players.AI;
import com.flederossi.players.Player;
import com.flederossi.ui.View;

public class GameLauncher {
    public static void main(String[] args) {
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };

        Board model = new Board(boardInit);
        View view = new View();

        // players[0] -> Player White | players[1] -> Player Black
        Object[] players = new Object[]{new Player(), new Player()};

        Game game = new Game(model, view, players);
        game.start();
    }
}
