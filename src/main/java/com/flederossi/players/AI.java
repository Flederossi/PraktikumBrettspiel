package com.flederossi.players;

import com.flederossi.game.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AI {
    private List<Move> getAvailableMoves(int id, Board board) {
        List<Move> availableMoves = new ArrayList<>();
        Coordinate currentPos;
        Move checkMove;
        Coordinate[] checkPos;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                currentPos = new Coordinate(x, y);
                checkPos = new Coordinate[]{new Coordinate(x, y - 1), new Coordinate(x, y + 1), new Coordinate(x - 1, y), new Coordinate(x + 1, y)};

                for (int i = 0; i < 4; i++) {
                    checkMove = new Move(currentPos, checkPos[i]);
                    if (board.checkLegalMove(checkMove, id)) {
                        availableMoves.add(checkMove);
                    }
                }
            }
        }
        return availableMoves;
    }

    private int[][] generateTestBoardArray(Board board) {
        int[][] testBoardArray = new int[5][5];
        for (int y = 0; y < 5; y++) {
            System.arraycopy(board.board[y], 0, testBoardArray[y], 0, 5);
        }
        return testBoardArray;
    }

    private int calculateHeuristicValue(int id, Board board) {
        int heuristicValue;

        int maxCount = 0;
        int[] count = {0, 0};
        int[][] boardArray = board.getBoard();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (boardArray[i][j] == 2) {
                    count[0]++;
                }
                if (boardArray[j][i] == 2) {
                    count[1]++;
                }
            }
            int[] calculatedValues = new int[]{maxCount, count[0], count[1]};
            Arrays.sort(calculatedValues);
            maxCount = calculatedValues[2];
            count = new int[]{0, 0};
        }
        heuristicValue = (maxCount - 1) * (-2 * id + 3); // -1 if id == 2 | 1 if id == 1
        return heuristicValue;
    }

    private int maximum(int id, Board board, int alpha, int beta, int depth) {
        List<Move> availableMoves = getAvailableMoves(id, board);
        int best = -1000;

        for (Move availableMove : availableMoves) {
            Board testBoard = new Board(generateTestBoardArray(board));
            testBoard.applyMove(availableMove);
            best = Math.max(best, minimax(testBoard, id, depth - 1, false, alpha, beta));
            alpha = Math.max(alpha, best);
            if (best >= beta) {
                break;
            }
        }
        return best;
    }

    private int minimum(int id, Board board, int alpha, int beta, int depth) {
        List<Move> availableMoves = getAvailableMoves(-id + 3, board);
        int best = 1000;

        for (Move availableMove : availableMoves) {
            Board testBoard = new Board(generateTestBoardArray(board));
            testBoard.applyMove(availableMove);
            best = Math.min(best, minimax(testBoard, id, depth - 1, true, alpha, beta));
            beta = Math.min(beta, best);
            if (best <= alpha) {
                break;
            }
        }
        return best;
    }

    private int minimax(Board board, int id, int depth, boolean max, int alpha, int beta) {
        // Check terminating state of the recursion
        int res = new WinLogic(board.getBoard()).checkWon(board.getBoard());
        if (res != 0) {
            return res == id ? 3 : -3;
        }
        if (depth < 1) {
            return calculateHeuristicValue(id, board);
        }

        // Calculate scores for next moves
        int best;
        if (max) {
            best = maximum(id, board, alpha, beta, depth);
        } else {
            best = minimum(id, board, alpha, beta, depth);
        }
        return best;
    }

    public Move generateNextMove(int id, Board board) {
        List<Move> availableMoves = getAvailableMoves(id, board);

        System.out.println();

        int bestValue = -1000;
        Move bestMove = new Move(new Coordinate(0, 0), new Coordinate(0, 0));

        for (int i = 0; i < availableMoves.size(); i++) {
            Board testBoard = new Board(generateTestBoardArray(board));
            testBoard.applyMove(availableMoves.get(i));

            // Change the initial depth value to control the speed and accuracy of the algorithm
            int moveValue = minimax(testBoard, id, 10, false, -1000, 1000);

            System.out.println("Found score: " + moveValue + " for move: (" + availableMoves.get(i).startPos.x + "|" + availableMoves.get(i).startPos.y + ") -> (" + availableMoves.get(i).endPos.x + "|" + availableMoves.get(i).endPos.y + ")");

            if (i == 0 || moveValue > bestValue) {
                bestValue = moveValue;
                bestMove = availableMoves.get(i);
            }
        }

        return bestMove;
    }
}
