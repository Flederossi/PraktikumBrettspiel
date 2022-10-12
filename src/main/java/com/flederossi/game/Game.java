package com.flederossi.game;

import com.flederossi.players.AI;
import com.flederossi.players.Player;
import com.flederossi.ui.View;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Game {
    protected final View ui;
    protected final WinLogic winLogic;

    protected final Board board;

    protected int currentPlayer;
    protected boolean gameEnded;

    protected Object[] players;

    public Game(Board board, View ui, Object[] players) {
        this.board = board;
        this.winLogic = new WinLogic(this.board.getBoard());
        this.currentPlayer = 2;
        this.gameEnded = false;
        this.ui = ui;

        this.players = players;
    }

    // Convert the int that represents the player to a string
    protected String convertIDToPlayer(int player) {
        return player == 1 ? "Weiß" : "Schwarz";
    }

    protected void switchCurrentPlayer() {
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
    }

    protected int makeChangesForRound(int clickX, int clickY) {
        Move move;

        if (this.players[this.currentPlayer - 1] instanceof AI) {
            this.ui.update(this.board, "Berechnung...", null);
            move = ((AI) players[currentPlayer - 1]).generateNextMove(currentPlayer, board);
        } else {
            move = ((Player) this.players[this.currentPlayer - 1]).generateNextMove(clickX, clickY);
        }

        if (move != null) {
            if (this.board.checkLegalMove(move, this.currentPlayer)) {
                this.board.applyMove(move);
                this.switchCurrentPlayer();
            } else {
                return -1;
            }
        }

        return 0;
    }

    void updateUI() {
        if (this.players[this.currentPlayer - 1] instanceof AI) {
            this.ui.update(this.board, convertIDToPlayer(this.currentPlayer) + " ist am Zug (Klicken)", null);
        } else {
            Player player = (Player) (this.players[this.currentPlayer - 1]);
            this.ui.update(this.board, convertIDToPlayer(this.currentPlayer) + " ist am Zug", player.firstClick ? new Coordinate(player.firstX, player.firstY) : null);
        }
    }

    // Event when mouse is clicked (main game logic)
    protected void onMouseEvent(MouseEvent mouseEvent) {
        if (!this.gameEnded) {
            int clickX = (int) Math.floor((float) ((mouseEvent.x - this.ui.getDisplayData()[1]) / this.ui.getDisplayData()[0]));
            int clickY = (int) Math.floor((float) ((mouseEvent.y - this.ui.getDisplayData()[2]) / this.ui.getDisplayData()[0]));

            int res = makeChangesForRound(clickX, clickY);

            updateUI();

            if (res == -1) {
                this.ui.update(this.board, "Zug ungültig", null);
            }

            this.winLogic.reloadPosBlack(this.board.getBoard());
            int won = this.winLogic.checkWon(this.board.getBoard());
            if (won > 0) {
                this.ui.update(this.board, convertIDToPlayer(won) + " hat gewonnen (Klicken)", null);
                this.gameEnded = true;
            }
        } else {
            restart();
        }
    }

    public void start() {
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                onMouseEvent(mouseEvent);
            }
        };

        this.ui.addEvent(ml);
        updateUI();
        this.ui.start();
    }

    public void restart() {
        this.board.setBoard(new int[][]{
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        });
        this.currentPlayer = 2;
        this.gameEnded = false;
        updateUI();
    }
}
