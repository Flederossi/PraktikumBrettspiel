package com.flederossi.ui;

import com.flederossi.game.Board;
import com.flederossi.game.Coordinate;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class View {
    private final Display display;
    private final Shell shell;
    private final Canvas view;
    private final Label status;

    public final static int size = 50, offsetX = 10, offsetY = 10;

    private final Color[] colors;

    public View() {
        display = new Display();
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

        shell.setText("Brettspiel");
        shell.setSize((int) (size * 5.25 + offsetX * 2.5), (int) (size * 5.25 + offsetY * 6.5));

        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = layout.marginWidth = 0;

        shell.setLayout(layout);

        colors = new Color[]{display.getSystemColor(SWT.COLOR_GRAY), display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_BLACK)};

        view = new Canvas(shell, SWT.NONE);
        view.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        status = new Label(shell, SWT.NONE);
        status.setAlignment(SWT.CENTER);
        status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        status.setFont(new Font(status.getDisplay(), new FontData("Calibri", 12, SWT.BOLD)));
    }

    public void update(Board board, String info, Coordinate firstClick) {
        view.addPaintListener(paintEvent -> {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (firstClick != null && firstClick.x == x && firstClick.y == y) {
                        paintEvent.gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
                    } else {
                        paintEvent.gc.setBackground(colors[board.getBoard()[y][x]]);
                    }
                    paintEvent.gc.setForeground(colors[2]);
                    int size = View.size;
                    int offsetX = View.offsetX;
                    int offsetY = View.offsetY;
                    paintEvent.gc.fillRectangle(x * size + offsetX, y * size + offsetY, size, size);
                    paintEvent.gc.drawRectangle(x * size + offsetX, y * size + offsetY, size, size);
                }
            }
        });
        view.redraw();
        view.update();
        status.setText(info);
    }

    public void addEvent(MouseListener ml) {
        view.addMouseListener(ml);
    }

    public void start() {
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

    public int[] getDisplayData() {
        return new int[]{size, offsetX, offsetY};
    }
}
