package com.flederossi.players;

import com.flederossi.game.Board;
import com.flederossi.game.Move;
import com.flederossi.interfaces.PlayerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AI implements PlayerImpl {
    @Override
    public Move generateNextMove(int id, int tileX, int tileY, Board board) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (board.checkLegalMove(new Move(id, 0, -1, x, y))){
                    availableMoves.add(new Move(id, 0, -1, x, y));
                }
                if (board.checkLegalMove(new Move(id, 0, 1, x, y))){
                    availableMoves.add(new Move(id, 0, 1, x, y));
                }
                if (board.checkLegalMove(new Move(id, -1, 0, x, y))){
                    availableMoves.add(new Move(id, -1, 0, x, y));
                }
                if (board.checkLegalMove(new Move(id, 1, 0, x, y))){
                    availableMoves.add(new Move(id, 1, 0, x, y));
                }

            }
        }

        // TODO Some fancy algorithm to search for the best moves :)

        System.out.println("\nFound " + availableMoves.size() + " moves");

        int randIndex = ThreadLocalRandom.current().nextInt(availableMoves.size());

        for (int i = 0; i < availableMoves.size(); i++){
            if (i == randIndex){
                System.out.print("> ");
            }
            System.out.println(availableMoves.get(i).debugMove());
        }

        return availableMoves.get(randIndex);
    }
}
