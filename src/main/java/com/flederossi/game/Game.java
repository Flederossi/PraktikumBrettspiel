package com.flederossi.game;

import com.flederossi.interfaces.GUI;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

abstract public class Game {
    protected final GUI ui;
    protected final WinLogic winLogic;

    protected final Board board;

    protected int currentPlayer;
    protected boolean gameEnded;

    protected Game(int[][] boardInit, GUI ui){
        this.board = new Board(boardInit);
        this.winLogic = new WinLogic(this.board.getBoard());
        this.currentPlayer = 2;
        this.gameEnded = false;
        this.ui = ui;
    }

    // Convert the int that represents the player to a string
    protected String convertIDToPlayer(int player){
        return player == 1 ? "Weiß" : "Schwarz";
    }

    protected void switchCurrentPlayer(){
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
    }

    protected int makeChangesForRound(int tileX, int tileY){
        return -1;
    }

    // Event when mouse is clicked (main game logic)
    protected void onMouseEvent(MouseEvent mouseEvent){
        if (!this.gameEnded) {
            int res = 0;

            int tileX = (int) Math.ceil((float) ((mouseEvent.x - this.ui.getDisplayData()[1]) / this.ui.getDisplayData()[0]));
            int tileY = (int) Math.ceil((float) ((mouseEvent.y - this.ui.getDisplayData()[2]) / this.ui.getDisplayData()[0]));

            if (tileX < 5 && tileY < 5) {
                res = makeChangesForRound(tileX, tileY);
            }

            this.ui.updateBoard(this.board.getBoard());
            this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");

            if (res == -1){
                this.ui.updateStatus("Zug ungültig");
            }

            this.winLogic.reloadPosBlack(this.board.getBoard());
            int won = this.winLogic.checkWon(this.board.getBoard());
            if (won > 0) {
                this.ui.updateStatus(convertIDToPlayer(won) + " hat gewonnen");
                this.gameEnded = true;
            }
        }
    }

    protected void start(){
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

        this.ui.addMouseListener(ml);

        this.ui.updateBoard(this.board.getBoard());
        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");

        this.ui.start();
    }
}
