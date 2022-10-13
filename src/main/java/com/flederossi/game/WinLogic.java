package com.flederossi.game;

public class WinLogic {
    private int[][] posBlack;

    public WinLogic(int[][] board) {
        reloadPosBlack(board);
    }

    public void reloadPosBlack(int[][] board) {
        posBlack = new int[2][3];

        int i = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (board[y][x] == 2 && i <= 2) {
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

    private boolean checkNeighbourFieldsEmpty(int x, int y, int[][] board) {
        return (y <= 0 || board[y - 1][x] == 0 || board[y - 1][x] == 2) && (y >= 4 || board[y + 1][x] == 0 || board[y + 1][x] == 2) && (x <= 0 || board[y][x - 1] == 0 || board[y][x - 1] == 2) && (x >= 4 || board[y][x + 1] == 0 || board[y][x + 1] == 2);
    }

    public int checkWon(int[][] board) {
        int res = 1;

        // Check win white
        if (checkOnlyContains(posBlack[0]) && checkOnlyContains(posBlack[1])) {
            res = 0;
        }

        // Check win black
        if (res == 0) {
            res = 2;
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (board[y][x] == 2) {
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
