package com.flederossi.ui;

import com.flederossi.game.Board;
import com.flederossi.game.Coordinate;
import com.flederossi.game.Game;
import com.flederossi.game.Move;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class View {
    private final Display display;
    private final Shell shell;
    private final Canvas view;
    private final Label status;
    private Game game;

    private final static int size = 50, offsetX = 10, offsetY = 10;

    private final Color[] colors;

    private int firstX = -1, firstY = -1;
    private boolean firstClick = false;

    public View() {
        display = new Display();
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

        shell.setText("Brettspiel");
        shell.setSize((int) (size * 5.25 + offsetX * 2.5), (int) (size * 5.25 + offsetY * 9));

        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = layout.marginWidth = 0;

        shell.setLayout(layout);

        colors = new Color[]{display.getSystemColor(SWT.COLOR_GRAY), display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_BLACK)};

        view = new Canvas(shell, SWT.NONE);
        view.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                int clickX = (int) Math.floor((float) ((mouseEvent.x - offsetX) / size));
                int clickY = (int) Math.floor((float) ((mouseEvent.y - offsetY) / size));

                if (firstClick){
                    Move move = new Move(new Coordinate(firstX, firstY), new Coordinate(clickX, clickY));
                    game.mouseEvent(move);
                }else{
                    firstX = clickX;
                    firstY = clickY;
                }
                update(game.getBoard(), game.getInfo());
                firstClick = !firstClick;
            }
        };

        view.addMouseListener(ml);

        status = new Label(shell, SWT.NONE);
        status.setAlignment(SWT.CENTER);
        status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        status.setFont(new Font(status.getDisplay(), new FontData("Calibri", 12, SWT.BOLD)));

        Menu gameMenu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(gameMenu);
        MenuItem gameMenuLBL = new MenuItem(gameMenu, SWT.CASCADE);
        gameMenuLBL.setText("Spiel");
        Menu gameSubMenu = new Menu(shell, SWT.DROP_DOWN);
        gameMenuLBL.setMenu(gameSubMenu);

        MenuItem restartItem = new MenuItem(gameSubMenu, SWT.PUSH);
        restartItem.addListener(SWT.Selection, event -> {
            firstX = firstY = -1;
            firstClick = false;
            game.restart();
            update(game.getBoard(), game.getInfo());
        });
        restartItem.setText("Neustart");
    }

    private void update(Board board, String info) {
        view.addPaintListener(paintEvent -> {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (!firstClick && firstX == x && firstY == y) {
                        paintEvent.gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
                    } else {
                        paintEvent.gc.setBackground(colors[board.getPlayer(x, y)]);
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

    public void start() {
        update(game.getBoard(), game.getInfo());

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
