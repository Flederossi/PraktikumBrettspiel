package com.flederossi.interfaces;

import org.eclipse.swt.events.MouseListener;

public interface GUIImpl {

    // Add the mouse listener to the board part
    void addMouseListener(MouseListener ml);

    // Update the board part of the GUI
    void updateBoard(int[][] board);

    // Update the status part of the GUI
    void updateStatus(String content);

    // Get size, x-offset and y-offset
    int[] getDisplayData();

    // Start the GUI
    void start();
}