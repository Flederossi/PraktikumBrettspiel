package com.flederossi.game;

import com.flederossi.interfaces.GUI;
import org.eclipse.swt.events.MouseEvent;

public class GameAgainstOtherPlayer extends Game{

    private final GUI ui;
    public final WinLogic winLogic;

    public final Board board;

    public int currentPlayer;
    private boolean gameEnded;

    private final Player[] players;

    GameAgainstOtherPlayer(int[][] board, GUI ui){
        super(board, ui);

        this.ui = super.ui;
        this.winLogic = super.winLogic;
        this.board = super.board;
        this.currentPlayer = super.currentPlayer;
        this.gameEnded = super.gameEnded;

        this.players = new Player[]{new Player(1), new Player(2)};
    }

    @Override
    public void onMouseEvent(MouseEvent mouseEvent){
        if (!this.gameEnded) {
            int tileX = (int) Math.ceil((float) ((mouseEvent.x - GameUI.offsetX) / GameUI.size));
            int tileY = (int) Math.ceil((float) ((mouseEvent.y - GameUI.offsetY) / GameUI.size));

            if (tileX < 5 && tileY < 5) {
                Move nextMove = this.players[this.currentPlayer - 1].generateNextMove(tileX, tileY);

                if (nextMove == null){
                    this.ui.updateBoard(this.board.getBoard(), this.players[currentPlayer - 1].firstX, this.players[currentPlayer - 1].firstY, false);
                }else{
                    if (this.board.checkLegalMove(nextMove)) {
                        this.board.applyMove(nextMove);
                        this.winLogic.reloadPosBlack(this.board.getBoard());
                        this.ui.updateBoard(this.board.getBoard(), this.players[currentPlayer - 1].firstX, this.players[currentPlayer - 1].firstY, true);
                        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
                        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");
                    } else {
                        this.ui.updateStatus("Zug ungültig");
                        this.ui.updateBoard(this.board.getBoard(), this.players[currentPlayer - 1].firstX, this.players[currentPlayer - 1].firstY, true);
                    }
                }

                int res = winLogic.checkWon(this.board.getBoard());
                if (res > 0) {
                    this.ui.updateStatus(convertIDToPlayer(res) + " hat gewonnen");
                    this.gameEnded = true;
                }
            }
        }
    }
}
