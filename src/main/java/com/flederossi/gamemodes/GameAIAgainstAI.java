package com.flederossi.gamemodes;

import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.interfaces.GUIImpl;
import com.flederossi.interfaces.PlayerImpl;
import com.flederossi.players.AI;
import com.flederossi.players.Player;
import org.eclipse.swt.events.MouseEvent;

public class GameAIAgainstAI extends Game {
    public GameAIAgainstAI(int[][] boardInit, GUIImpl ui){
        super(boardInit, ui);
        super.players = new PlayerImpl[]{new AI(), new AI()};
    }

    private Move generateAIMove(){
        return super.players[super.currentPlayer - 1].generateNextMove(super.currentPlayer, 0, 0, super.board);
    }

    @Override
    protected int makeChangesForRound(int tileX, int tileY){
        System.out.println("AI: " + super.currentPlayer);
        super.board.applyMove(this.generateAIMove());
        super.switchCurrentPlayer();

        return 0;
    }

    @Override
    protected void onMouseEvent(MouseEvent mouseEvent) {
        while (!this.gameEnded) {
            super.onMouseEvent(mouseEvent);
        }
    }

    @Override
    protected void start(){
        super.start();
    }
}
