package com.flederossi.ui;

import com.flederossi.game.Board;
import org.eclipse.swt.events.MouseListener;

import java.lang.reflect.Method;

public interface GUI {
    // Update the board part of the GUI
    void update(Board board, String info);

    void addEvent(MouseListener ml);

    // Get size, x-offset and y-offset
    int[] getDisplayData();

    // Start the GUI
    void start();
}
