package com.flederossi.game;

public class WinLogic {
    int[][] posBlack;

    WinLogic(int[][] board){
        reloadPosBlack(board);
    }

    void reloadPosBlack(int[][] board){
        this.posBlack = new int[2][3];

        int i = 0;
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (board[x][y] == 2 && i <= 2){
                    this.posBlack[0][i] = x;
                    this.posBlack[1][i] = y;
                    i++;
                }
            }
        }
    }

    boolean checkOnlyContains(int[] array){
        boolean res = true;
        int num = array[0];
        for (int i = 1; i < array.length; i++){
            if (array[i] != num){
                res = false;
                break;
            }
        }
        return !res;
    }

    boolean checkNeighbourFieldsEmpty(int x, int y, int[][] board){
        return (y <= 0 || board[x][y - 1] == 0) && (y >= 4 || board[x][y + 1] == 0) && (x <= 0 || board[x - 1][y] == 0) && (x >= 4 || board[x + 1][y] == 0);
    }

    int checkWon(int[][] board){
        int res = 1;

        // Check win white
        if (checkOnlyContains(this.posBlack[0]) && checkOnlyContains(this.posBlack[1])){
            res = 0;
        }

        // Check win black
        if (res == 0){
            res = 2;
            for (int y = 0; y < 5; y++){
                for (int x = 0; x < 5; x++){
                    if (board[x][y] == 2){
                        if (!checkNeighbourFieldsEmpty(x, y, board)){
                            res = 0;
                        }
                    }
                }
            }
        }

        return res;
    }
}
