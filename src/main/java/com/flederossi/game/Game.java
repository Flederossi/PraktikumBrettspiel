package com.flederossi.game;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Game {
    private final GUI ui;
    private final WinLogic winLogic;

    private final Board board;

    private int currentPlayer, firstX, firstY;
    private boolean firstClick, gameEnded;

    protected Game(int[][] boardInit, GUI ui){
        this.board = new Board(boardInit);
        this.winLogic = new WinLogic(this.board.getBoard());
        this.currentPlayer = 2;
        this.firstClick = this.gameEnded = false;
        this.firstX = this.firstY = -1;
        this.ui = ui;
    }

    // Convert the int that represents the player to a string
    private String convertIDToPlayer(int player){
        return player == 1 ? "Weiß" : "Schwarz";
    }

    // Convert two coordinates to the shift of a move
    private int[] convertCoordsToShift(int x1, int y1, int x2, int y2){
        return new int[]{x2 - x1, y2 - y1};
    }

    // Event when mouse is clicked (main game logic)
    private void onMouseEvent(MouseEvent mouseEvent){
        if (!this.gameEnded) {
            // Convert screen coordinates to tile coordinates
            int tileX = (int) Math.ceil((float) ((mouseEvent.x - GameUI.offsetX) / GameUI.size));
            int tileY = (int) Math.ceil((float) ((mouseEvent.y - GameUI.offsetY) / GameUI.size));

            // Check if mouse click is outside the board
            if (tileX < 5 && tileY < 5) {
                // Check if the tile is already selected
                if (firstClick) {
                    // Generate the move the player is going to play
                    int sX = convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[0];
                    int sY = convertCoordsToShift(this.firstX, this.firstY, tileX, tileY)[1];
                    Move currentMove = new Move(this.currentPlayer, sX, sY, this.firstX, this.firstY);

                    // Check if the move is legal and apply if so
                    if (this.board.checkLegalMove(currentMove)) {
                        this.board.applyMove(currentMove);
                        this.winLogic.reloadPosBlack(this.board.getBoard());
                        this.ui.updateBoard(this.board.getBoard(), this.firstX, this.firstY, this.firstClick);
                        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
                        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");
                    } else {
                        this.ui.updateStatus("Zug ungültig");
                        this.ui.updateBoard(this.board.getBoard(), this.firstX, this.firstY, this.firstClick);
                    }
                } else {
                    this.firstX = tileX;
                    this.firstY = tileY;
                    this.ui.updateBoard(this.board.getBoard(), this.firstX, this.firstY, false);
                }

                this.firstClick = !this.firstClick;

                // Check if one player won the game
                int res = winLogic.checkWon(this.board.getBoard());
                if (res > 0) {
                    this.ui.updateStatus(convertIDToPlayer(res) + " hat gewonnen");
                    this.gameEnded = true;
                }
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

        this.ui.setMouseListener(ml);

        this.ui.updateBoard(this.board.getBoard(), this.firstX, this.firstY, this.firstClick);
        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");

        this.ui.start();
    }
}
