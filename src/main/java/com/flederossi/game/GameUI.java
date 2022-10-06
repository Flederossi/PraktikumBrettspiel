package com.flederossi.game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GameUI {
    private final Display display;
    private final Shell shell;
    private final Canvas view;
    private final Label status;

    protected final static int size = 50, offsetX = 10, offsetY = 10;

    private final Color[] colors;

    protected GameUI(MouseListener ml){
        this.display = new Display();
        this.shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

        this.shell.setText("Brettspiel");
        this.shell.setSize((int)(size * 5.25 + offsetX * 2.5), (int)(size * 5.25 + offsetY * 6.5));

        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = layout.marginWidth = 0;

        this.shell.setLayout(layout);

        this.colors = new Color[] {display.getSystemColor(SWT.COLOR_GRAY), display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_BLACK)};

        this.view = new Canvas(this.shell, SWT.NONE);
        this.view.addMouseListener(ml);
        this.view.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        this.status = new Label(this.shell, SWT.NONE);
        this.status.setAlignment(SWT.CENTER);
        this.status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        this.status.setFont(new Font(this.status.getDisplay(), new FontData("Calibri", 12, SWT.BOLD)));
    }

    protected void updateBoard(int[][] board, int firstX, int firstY, boolean firstClick){
        this.view.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {
                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (firstX == x && firstY == y && !firstClick){
                            paintEvent.gc.setBackground(GameUI.this.display.getSystemColor(SWT.COLOR_RED));
                        }else {
                            paintEvent.gc.setBackground(GameUI.this.colors[board[y][x]]);
                        }
                        paintEvent.gc.setForeground(GameUI.this.colors[2]);
                        int size = GameUI.size;
                        int offsetX = GameUI.offsetX;
                        int offsetY = GameUI.offsetY;
                        paintEvent.gc.fillRectangle(x * size + offsetX, y * size + offsetY, size, size);
                        paintEvent.gc.drawRectangle(x * size + offsetX, y * size + offsetY, size, size);
                    }
                }
            }
        });
        this.view.redraw();
        this.view.update();
    }

    protected void updateStatus(String content){
        this.status.setText(content);
    }

    protected void start(){
        this.shell.open();

        while (!this.shell.isDisposed()){
            if (!this.display.readAndDispatch()){
                this.display.sleep();
            }
        }

        this.display.dispose();
    }
}
