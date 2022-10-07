package com.flederossi.game;

import com.flederossi.interfaces.GUI;
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

public class GameView implements GUI {
    private final Display display;
    private final Shell shell;
    private final Canvas view;
    private final Label status;

    public final static int size = 50, offsetX = 10, offsetY = 10;

    private final Color[] colors;

    protected GameView(){
        this.display = new Display();
        this.shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

        this.shell.setText("Brettspiel");
        this.shell.setSize((int)(size * 5.25 + offsetX * 2.5), (int)(size * 5.25 + offsetY * 6.5));

        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = layout.marginWidth = 0;

        this.shell.setLayout(layout);

        this.colors = new Color[] {display.getSystemColor(SWT.COLOR_GRAY), display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_BLACK)};

        this.view = new Canvas(this.shell, SWT.NONE);
        this.view.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        this.status = new Label(this.shell, SWT.NONE);
        this.status.setAlignment(SWT.CENTER);
        this.status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        this.status.setFont(new Font(this.status.getDisplay(), new FontData("Calibri", 12, SWT.BOLD)));
    }

    @Override
    public void addMouseListener(MouseListener ml){
        this.view.addMouseListener(ml);
    }

    @Override
    public void updateBoard(int[][] board){
        this.view.addPaintListener(paintEvent -> {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    paintEvent.gc.setBackground(this.colors[board[y][x]]);
                    paintEvent.gc.setForeground(this.colors[2]);
                    int size = GameView.size;
                    int offsetX = GameView.offsetX;
                    int offsetY = GameView.offsetY;
                    paintEvent.gc.fillRectangle(x * size + offsetX, y * size + offsetY, size, size);
                    paintEvent.gc.drawRectangle(x * size + offsetX, y * size + offsetY, size, size);
                }
            }
        });
        this.view.redraw();
        this.view.update();
    }

    @Override
    public void updateStatus(String content){
        this.status.setText(content);
    }

    @Override
    public int[] getDisplayData(){
        return new int[]{size, offsetX, offsetY};
    }

    @Override
    public void start(){
        this.shell.open();

        while (!this.shell.isDisposed()){
            if (!this.display.readAndDispatch()){
                this.display.sleep();
            }
        }

        this.display.dispose();
    }
}