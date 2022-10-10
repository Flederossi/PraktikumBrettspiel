package com.flederossi.players;

import com.flederossi.game.Board;
import com.flederossi.game.Coordinate;
import com.flederossi.game.Move;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AI {
    public Move generateNextMove(int id, Board board) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        Coordinate currentPos;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                currentPos = new Coordinate(x, y);
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x, y - 1)), id)){
                    availableMoves.add(new Move(currentPos, new Coordinate(x, y - 1)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x, y + 1)), id)){
                    availableMoves.add(new Move(currentPos, new Coordinate(x, y + 1)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x - 1, y)), id)){
                    availableMoves.add(new Move(currentPos, new Coordinate(x - 1, y)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x + 1, y)), id)){
                    availableMoves.add(new Move(currentPos, new Coordinate(x + 1, y)));
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
