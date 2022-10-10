package com.flederossi.players;

import com.flederossi.game.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AI {

    void printMainBoard(Board board) {
        for (int i = 0; i < board.getBoard().length; i++) {
            System.out.println(Arrays.toString(board.getBoard()[i]));
        }
    }

    public Move generateNextMove(int id, Board board) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        ArrayList<Integer> neutralMoves = new ArrayList<>();
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

        System.out.println("\nFound " + availableMoves.size() + " moves");

        boolean foundWinMove = false;
        int index = ThreadLocalRandom.current().nextInt(availableMoves.size());

        for (int i = 0; i < availableMoves.size(); i++){
            board.applyMove(availableMoves.get(i));
            int res = new WinLogic(board.getBoard()).checkWon(board.getBoard());
            board.applyMove(new Move(availableMoves.get(i).endPos, availableMoves.get(i).startPos));
            board.board[availableMoves.get(i).endPos.y][availableMoves.get(i).endPos.x] = id - 1;
            if (res == id){
                index = i;
                foundWinMove = true;
                System.out.println("Found move to win");
                break;
            }else if (res == 0){
                neutralMoves.add(i);
            }else{
                System.out.println("Found move to lose");
            }
        }

        if (!foundWinMove && neutralMoves.size() > 0){
            index = neutralMoves.get(ThreadLocalRandom.current().nextInt(neutralMoves.size()));
        }

        for (int i = 0; i < availableMoves.size(); i++){
            if (i == index){
                System.out.print("> ");
            }
            System.out.println(availableMoves.get(i).debugMove());
        }

        return availableMoves.get(index);
    }
}
