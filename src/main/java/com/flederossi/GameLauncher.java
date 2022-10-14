package com.flederossi;

import com.flederossi.game.Board;
import com.flederossi.game.Game;
import com.flederossi.players.AI;
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
        Game game = new Game(model);

        view.setGame(game);
        view.start();
    }
}
