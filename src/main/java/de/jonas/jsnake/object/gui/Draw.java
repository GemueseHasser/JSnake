package de.jonas.jsnake.object.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import de.jonas.jsnake.object.Apple;
import de.jonas.jsnake.object.SnakeField;
import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public final class Draw extends JLabel {

    private static final int GAME_RECT_X = 50;
    private static final int GAME_RECT_Y = 30;
    private static final int GAME_RECT_SIZE = 15;

    private static final int GAME_RECTS_SIZE = 27;


    private int x;
    private int y;


    public Draw() {
        y = GAME_RECT_Y;
        for (int i = 0; i < GAME_RECT_SIZE; i++) {
            x = GAME_RECT_X;
            for (int j = 0; j < GAME_RECT_SIZE; j++) {
                // calculate field number
                final int fieldNumber = i * GAME_RECT_SIZE + j;

                // create snake-field
                final SnakeField field = new SnakeField(fieldNumber);

                // save snake-field
                JSnake.SNAKE_FIELDS.put(fieldNumber, field);
            }
        }
    }


    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        y = GAME_RECT_Y;

        for (int i = 0; i < GAME_RECT_SIZE; i++) {
            x = GAME_RECT_X;
            for (int j = 0; j < GAME_RECT_SIZE; j++) {
                // calculate field number
                final int fieldNumber = i * GAME_RECT_SIZE + j;

                // create snake-field
                final SnakeField field = JSnake.SNAKE_FIELDS.get(fieldNumber);

                // draw rect
                g.setColor(field.getState().getColor());

                if (field.getState() == SnakeFieldState.APPLE) {
                    // draw apple
                    g.fillOval(x + (GAME_RECTS_SIZE / 2), y + (GAME_RECTS_SIZE / 2), Apple.SIZE, Apple.SIZE);
                } else {
                    // draw default
                    g.fillRect(x, y, GAME_RECTS_SIZE, GAME_RECTS_SIZE);
                }

                // draw rect
                g.setColor(Color.BLACK);
                g.drawRect(x, y, GAME_RECTS_SIZE, GAME_RECTS_SIZE);

                x += GAME_RECTS_SIZE;
            }
            y += GAME_RECTS_SIZE;
        }

        repaint();
    }
}
