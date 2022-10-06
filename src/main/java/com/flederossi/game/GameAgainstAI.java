package com.flederossi.game;

import com.flederossi.interfaces.GUI;
import org.eclipse.swt.events.MouseEvent;

public class GameAgainstAI extends Game{

    private final GUI ui;
    public final WinLogic winLogic;

    public final Board board;

    public int currentPlayer;
    private boolean gameEnded;

    private Player player;
    private AI ai;

    private Object[] players;

    protected GameAgainstAI(int[][] board, GUI ui){
        super(board, ui);

        this.ui = super.ui;
        this.winLogic = super.winLogic;
        this.board = super.board;
        this.currentPlayer = super.currentPlayer;
        this.gameEnded = super.gameEnded;

        players = new Object[]{new Player(1), new AI(2)};
    }

    @Override
    protected void onMouseEvent(MouseEvent mouseEvent){
        if (!this.gameEnded){

        }
    }
}
