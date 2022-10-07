package com.flederossi.players;

import com.flederossi.game.Board;
import com.flederossi.game.Move;
import com.flederossi.interfaces.PlayerImpl;

public class AI implements PlayerImpl {
    @Override
    public Move generateNextMove(int id, int tileX, int tileY, Board board) {
        Move nextMove = null;

        int[][] boardContent = board.getBoard();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                nextMove = board.checkLegalMove(new Move(id, 0, -1, x, y)) ? new Move(id, 0, -1, x, y) : nextMove;
                nextMove = board.checkLegalMove(new Move(id, 0, 1, x, y)) ? new Move(id, 0, 1, x, y) : nextMove;
                nextMove = board.checkLegalMove(new Move(id, -1, 0, x, y)) ? new Move(id, -1, 0, x, y) : nextMove;
                nextMove = board.checkLegalMove(new Move(id, 1, 0, x, y)) ? new Move(id, 1, 0, x, y) : nextMove;
                if (nextMove != null){
                    System.out.println("Found move");
                    break;
                }
            }
            if (nextMove != null){
                break;
            }
        }

        return nextMove;
    }
}
