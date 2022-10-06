package com.flederossi.gamemode;

import com.flederossi.game.Board;
import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.game.WinLogic;
import com.flederossi.interfaces.GUI;
import com.flederossi.player.AI;
import com.flederossi.player.Player;
import org.eclipse.swt.events.MouseEvent;

public class GameAgainstAI extends Game {
    private final GUI ui;
    public final WinLogic winLogic;

    public final Board board;

    public int currentPlayer;
    private boolean gameEnded;

    private final Object[] players;
    private static final int aiID = 1;

    public GameAgainstAI(int[][] boardInit, GUI ui){
        super(boardInit, ui);

        this.ui = super.ui;
        this.winLogic = super.winLogic;
        this.board = super.board;
        this.currentPlayer = super.currentPlayer;
        this.gameEnded = super.gameEnded;

        this.players = new Object[]{new AI(1), new Player(2)};
    }

    // TODO Create new function to check and apply a move from the ai and the player

    @Override
    protected void onMouseEvent(MouseEvent mouseEvent){
        Player player = (Player) this.players[currentPlayer - 1];

        if (!this.gameEnded) {
            int tileX = (int) Math.ceil((float) ((mouseEvent.x - this.ui.getDisplayData()[1]) / this.ui.getDisplayData()[0]));
            int tileY = (int) Math.ceil((float) ((mouseEvent.y - this.ui.getDisplayData()[2]) / this.ui.getDisplayData()[0]));

            if (tileX < 5 && tileY < 5) {
                Move nextMove = player.generateNextMove(tileX, tileY, this.board.getBoard());

                if (nextMove == null){
                    this.ui.updateBoard(this.board.getBoard(), player.firstX, player.firstY, false);
                }else{
                    if (this.board.checkLegalMove(nextMove)) {
                        this.board.applyMove(nextMove);
                        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;

                        nextMove = ((AI) this.players[currentPlayer - 1]).generateNextMove(tileX, tileY, this.board.getBoard());

                        this.board.applyMove(nextMove);

                        this.winLogic.reloadPosBlack(this.board.getBoard());
                        this.ui.updateBoard(this.board.getBoard(), player.firstX, player.firstY, true);
                        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
                        this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");
                    } else {
                        this.ui.updateStatus("Zug ungültig");
                        this.ui.updateBoard(this.board.getBoard(), player.firstX, player.firstY, true);
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

    @Override
    protected void start(){
        if (aiID == 2){
            System.out.println("Found AI");
            this.currentPlayer = 1;
            this.ui.updateStatus(convertIDToPlayer(this.currentPlayer) + " ist am Zug");
        }

        super.start();
    }
}
