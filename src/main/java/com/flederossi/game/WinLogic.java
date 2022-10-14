package com.flederossi.game;

public class WinLogic {
    private int[][] posBlack;

    public WinLogic(Board board) {
        reloadPosBlack(board);
    }

    public void reloadPosBlack(Board board) {
        posBlack = new int[2][3];

        int i = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (board.getPlayer(x, y) == 2 && i <= 2) {
                    posBlack[0][i] = x;
                    posBlack[1][i] = y;
                    i++;
                }
            }
        }
    }

    private boolean checkOnlyContains(int[] array) {
        boolean res = true;
        int num = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != num) {
                res = false;
                break;
            }
        }
        return !res;
    }

    private boolean checkNeighbourFieldsEmpty(int x, int y, Board board) {
        return (y <= 0 || board.getPlayer(x, y-1) == 0 || board.getPlayer(x, y-1) == 2) && (y >= 4 || board.getPlayer(x, y+1) == 0 || board.getPlayer(x, y+1) == 2) && (x <= 0 || board.getPlayer(x-1, y) == 0 || board.getPlayer(x-1, y) == 2) && (x >= 4 || board.getPlayer(x+1, y) == 0 || board.getPlayer(x+1, y) == 2);
    }

    public int checkWon(Board board) {
        int res = 1;

        //reloadPosBlack(board);

        // Check win white
        if (checkOnlyContains(posBlack[0]) && checkOnlyContains(posBlack[1])) {
            res = 0;
        }

        // Check win black
        if (res == 0) {
            res = 2;
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (board.getPlayer(x, y) == 2) {
                        if (!checkNeighbourFieldsEmpty(x, y, board)) {
                            res = 0;
                        }
                    }
                }
            }
        }

        return res;
    }
}
