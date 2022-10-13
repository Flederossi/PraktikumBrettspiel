package com.flederossi.game;

import com.flederossi.players.AI;
import com.flederossi.players.Player;
import com.flederossi.ui.View;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Game {
    protected final View ui;
    protected final WinLogic winLogic;

    protected Board board;

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
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    protected int makeChangesForRound(int clickX, int clickY) {
        Move move;

        if (players[currentPlayer - 1] instanceof AI) {
            ui.update(board, "Berechnung...", null);
            move = ((AI) players[currentPlayer - 1]).generateNextMove(currentPlayer, board);
        } else {
            move = ((Player) players[currentPlayer - 1]).generateNextMove(clickX, clickY);
        }

        if (move != null) {
            if (checkLegalMove(board, move, currentPlayer)) {
                board = applyMove(board, move);
                switchCurrentPlayer();
            } else {
                return -1;
            }
        }

        return 0;
    }

    private static boolean isOrthogonal(Move move){
        return Math.abs(move.endPos.x - move.startPos.x) + Math.abs(move.endPos.y - move.startPos.y) == 1;
        //return move.endPos.x - move.startPos.x == 0 ^ move.endPos.y - move.startPos.y == 0;
    }

    private static boolean isInside(Move move){
        return move.endPos.x >= 0 && move.endPos.x <= 4 && move.endPos.y >= 0 && move.endPos.y <= 4;
    }

    public static boolean checkLegalMove(Board board, Move move, int player) {
        return isInside(move) && board.getPlayer(move.startPos) == player && isOrthogonal(move) && board.getPlayer(move.endPos) == player - 1;
    }

    public static Board applyMove(Board board, Move move){
        Board appliedBoard = new Board(board.getBoard());
        appliedBoard = appliedBoard.setPlayer(move.endPos, appliedBoard.getPlayer(move.startPos));
        appliedBoard = appliedBoard.setPlayer(move.startPos, 0);
        return appliedBoard;
    }

    void updateUI() {
        if (players[currentPlayer - 1] instanceof AI) {
            ui.update(board, convertIDToPlayer(currentPlayer) + " ist am Zug (Klicken)", null);
        } else {
            Player player = (Player) (players[currentPlayer - 1]);
            ui.update(board, convertIDToPlayer(currentPlayer) + " ist am Zug", player.firstClick ? new Coordinate(player.firstX, player.firstY) : null);
        }
    }

    // Event when mouse is clicked (main game logic)
    protected void onMouseEvent(MouseEvent mouseEvent) {
        if (!gameEnded) {
            int clickX = (int) Math.floor((float) ((mouseEvent.x - ui.getDisplayData()[1]) / ui.getDisplayData()[0]));
            int clickY = (int) Math.floor((float) ((mouseEvent.y - ui.getDisplayData()[2]) / ui.getDisplayData()[0]));

            int res = makeChangesForRound(clickX, clickY);

            updateUI();

            if (res == -1) {
                ui.update(board, "Zug ungültig", null);
            }

            winLogic.reloadPosBlack(board.getBoard());
            int won = winLogic.checkWon(board.getBoard());
            if (won > 0) {
                ui.update(board, convertIDToPlayer(won) + " hat gewonnen (Klicken)", null);
                gameEnded = true;
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

        ui.addEvent(ml);
        updateUI();
        ui.start();
    }

    public void restart() {
        board.setBoard(new int[][]{
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        });
        currentPlayer = 2;
        gameEnded = false;
        updateUI();
    }
}
