package com.flederossi.players;

import com.flederossi.game.*;

import java.util.ArrayList;
import java.util.List;

public class AI {
    private List<Move> getAvailableMoves(int id, Board board) {
        List<Move> availableMoves = new ArrayList<>();
        Coordinate currentPos;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                currentPos = new Coordinate(x, y);
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x, y - 1)), id)) {
                    availableMoves.add(new Move(currentPos, new Coordinate(x, y - 1)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x, y + 1)), id)) {
                    availableMoves.add(new Move(currentPos, new Coordinate(x, y + 1)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x - 1, y)), id)) {
                    availableMoves.add(new Move(currentPos, new Coordinate(x - 1, y)));
                }
                if (board.checkLegalMove(new Move(currentPos, new Coordinate(x + 1, y)), id)) {
                    availableMoves.add(new Move(currentPos, new Coordinate(x + 1, y)));
                }

            }
        }
        return availableMoves;
    }

    private int[][] generateTestBoardArray(Board board) {
        int[][] testBoardArray = new int[5][5];

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                testBoardArray[y][x] = board.board[y][x];
            }
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
            if (maxCount < count[0]) {
                maxCount = count[0];
            }
            if (maxCount < count[1]) {
                maxCount = count[1];
            }
            count = new int[]{0, 0};
        }

        if (id == 2) {
            heuristicValue = -maxCount;
        } else {
            heuristicValue = maxCount;
        }

        return heuristicValue;
    }

    private int minimax(Board board, int id, int depth, boolean max, int alpha, int beta) {
        int res = new WinLogic(board.getBoard()).checkWon(board.getBoard());

        if (res == id) {
            return 5;
        } else if (res == -id + 3) {
            return -5;
        }

        if (depth < 1) {
            return calculateHeuristicValue(id, board);
        }

        List<Move> availableMoves;
        int best;

        if (max) {
            availableMoves = getAvailableMoves(id, board);
            best = -1000;

            for (Move availableMove : availableMoves) {
                Board testBoard = new Board(generateTestBoardArray(board));
                testBoard.applyMove(availableMove);
                best = Math.max(best, minimax(testBoard, id, depth - 1, false, alpha, beta));
                alpha = Math.max(alpha, best);
                if (best >= beta) {
                    break;
                }
            }
        } else {
            availableMoves = getAvailableMoves(-id + 3, board);
            best = 1000;

            for (Move availableMove : availableMoves) {
                Board testBoard = new Board(generateTestBoardArray(board));
                testBoard.applyMove(availableMove);
                best = Math.min(best, minimax(testBoard, id, depth - 1, true, alpha, beta));
                beta = Math.min(beta, best);
                if (best <= alpha) {
                    break;
                }
            }
        }
        return best;
    }

    public Move generateNextMove(int id, Board board) {
        List<Move> availableMoves = getAvailableMoves(id, board);

        //System.out.println();

        int bestValue = -2;
        Move bestMove = new Move(new Coordinate(0, 0), new Coordinate(0, 0));

        for (int i = 0; i < availableMoves.size(); i++) {
            Board testBoard = new Board(generateTestBoardArray(board));
            testBoard.applyMove(availableMoves.get(i));

            int moveValue = minimax(testBoard, id, 10, false, -1000, 1000);

            //System.out.println("Found score: " + moveValue + " for move: (" + availableMoves.get(i).startPos.x + "|" + availableMoves.get(i).startPos.y + ") -> (" + availableMoves.get(i).endPos.x + "|" + availableMoves.get(i).endPos.y + ")");

            if (i == 0 || moveValue > bestValue) {
                bestValue = moveValue;
                bestMove = availableMoves.get(i);
            }
        }

        return bestMove;
    }
}
