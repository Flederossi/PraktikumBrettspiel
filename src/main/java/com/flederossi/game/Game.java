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

    // Event when mouse is clicked (main game logic)
    protected void onMouseEvent(MouseEvent mouseEvent){

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

        this.ui.updateBoard(this.board.getBoard(), -1, -1, true);
        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");

        this.ui.start();
    }
}
