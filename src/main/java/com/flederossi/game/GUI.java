package com.flederossi.game;

import org.eclipse.swt.events.MouseListener;

public interface GUI {

    // Add the mouse listener to the board part
    void setMouseListener(MouseListener ml);

    // Update the board part of the GUI
    void updateBoard(int[][] board, int firstX, int firstY, boolean firstClick);

    // Update the status part of the GUI
    void updateStatus(String content);

    // Start the GUI
    void start();
}
