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

        AI[] ais = new AI[]{null, new AI()};
        Game game = new Game(model, ais);

        view.setGame(game);
        view.start();
    }
}
