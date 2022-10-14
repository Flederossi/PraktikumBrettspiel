package com.flederossi.game;

import com.flederossi.players.AI;

public class Game {
    private final WinLogic winLogic;

    private Board board;
    private AI[] ais;

    private int currentPlayer;
    private boolean gameEnded;

    private String info;

    public Game(Board board) {
        this.board = board;
        this.ais = new AI[]{null, null};
        this.winLogic = new WinLogic(board);
        this.currentPlayer = 2;
        this.gameEnded = false;

        checkAIShouldMove();

        this.info = convertIDToPlayer(currentPlayer) + " ist am Zug";
    }

    // Convert the int that represents the player to a string
    protected String convertIDToPlayer(int player) {
        return player == 1 ? "Weiß" : "Schwarz";
    }

    protected void switchCurrentPlayer() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    private static boolean isOrthogonal(Move move){
        return Math.abs(move.endPos.x - move.startPos.x) + Math.abs(move.endPos.y - move.startPos.y) == 1;
        //return move.endPos.x - move.startPos.x == 0 ^ move.endPos.y - move.startPos.y == 0;
    }

    private static boolean isInside(Move move){
        return move.endPos.x >= 0 && move.endPos.x <= 4 && move.endPos.y >= 0 && move.endPos.y <= 4;
    }

    public static boolean checkLegalMove(Board board, Move move, int player) {
        return isInside(move) && board.getPlayer(move.startPos) == player && isOrthogonal(move) && board.getPlayer(move.endPos) == player - 1;
    }

    public static Board applyMove(Board board, Move move){
        Board appliedBoard = board;
        appliedBoard = appliedBoard.setPlayer(move.endPos, appliedBoard.getPlayer(move.startPos));
        appliedBoard = appliedBoard.setPlayer(move.startPos, 0);
        return appliedBoard;
    }

    private void checkAIShouldMove(){
        if (ais[currentPlayer - 1] != null) {
            board = applyMove(board, ais[currentPlayer - 1].generateNextMove(currentPlayer, board));
            switchCurrentPlayer();
        }
    }

    public void mouseEvent(Move move){
        if (!gameEnded) {
            if (checkLegalMove(board, move, currentPlayer)) {
                board = applyMove(board, move);
                switchCurrentPlayer();

                checkAIShouldMove();

                info = convertIDToPlayer(currentPlayer) + " ist am Zug";

                winLogic.reloadPosBlack(board);
                int won = winLogic.checkWon(board);
                if (won > 0) {
                    info = convertIDToPlayer(won) + " hat gewonnen";
                    gameEnded = true;
                }
            }else{
                info = "Zug ungültig";
            }
        }
    }

    public String getInfo(){
        return info;
    }

    public Board getBoard(){
        return board;
    }

    public void setGameMode(AI[] ais){
        this.ais = ais;
    }

    public void restart() {
        board = new Board(new int[][]{
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        });
        currentPlayer = 2;
        gameEnded = false;
        checkAIShouldMove();
    }
}
