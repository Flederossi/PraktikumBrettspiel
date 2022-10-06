package com.flederossi.gamemode;

import com.flederossi.game.*;
import com.flederossi.interfaces.GUI;
import com.flederossi.player.Player;
import org.eclipse.swt.events.MouseEvent;

public class GameAgainstOtherPlayer extends Game {

    private final GUI ui;
    private final WinLogic winLogic;

    private final Board board;

    private int currentPlayer;
    private boolean gameEnded;

    private final Player[] players;

    public GameAgainstOtherPlayer(int[][] board, GUI ui){
        super(board, ui);

        this.ui = super.ui;
        this.winLogic = super.winLogic;
        this.board = super.board;
        this.currentPlayer = super.currentPlayer;
        this.gameEnded = super.gameEnded;

        this.players = new Player[]{new Player(1), new Player(2)};
    }

    @Override
    protected void onMouseEvent(MouseEvent mouseEvent){
        if (!this.gameEnded) {
            int tileX = (int) Math.ceil((float) ((mouseEvent.x - this.ui.getDisplayData()[1]) / this.ui.getDisplayData()[0]));
            int tileY = (int) Math.ceil((float) ((mouseEvent.y - this.ui.getDisplayData()[2]) / this.ui.getDisplayData()[0]));

            if (tileX < 5 && tileY < 5) {
                Move nextMove = this.players[this.currentPlayer - 1].generateNextMove(tileX, tileY, this.board.getBoard());

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
