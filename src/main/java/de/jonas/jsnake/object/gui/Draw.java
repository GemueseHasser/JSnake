package de.jonas.jsnake.object.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import de.jonas.jsnake.object.Apple;
import de.jonas.jsnake.object.SnakeField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@NotNull
public final class Draw extends JLabel {

    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int GAME_RECT_X = 50;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int GAME_RECT_Y = 30;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int GAME_RECT_SIZE = 15;

    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int GAME_RECTS_SIZE = 27;

    @NotNull
    private static final Font INFORMATION_FONT = new Font("Arial", Font.BOLD, 20);

    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int APPLE_SPAWN_TIMER_X = 500;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int APPLE_SPAWN_TIMER_Y = 100;


    public Draw() {
        for (int i = 0; i < GAME_RECT_SIZE; i++) {
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

        // draw game-rect
        int y = GAME_RECT_Y;

        for (int i = 0; i < GAME_RECT_SIZE; i++) {
            int x = GAME_RECT_X;
            for (int j = 0; j < GAME_RECT_SIZE; j++) {
                // calculate field number
                final int fieldNumber = i * GAME_RECT_SIZE + j;

                // create snake-field
                final SnakeField field = JSnake.SNAKE_FIELDS.get(fieldNumber);

                // draw rect
                g.setColor(field.getState().getColor());

                if (field.getState() == SnakeFieldState.APPLE) {
                    // draw apple
                    g.fillOval(
                        x + (GAME_RECTS_SIZE / 2) - Apple.SIZE / 2,
                        y + (GAME_RECTS_SIZE / 2) - Apple.SIZE / 2,
                        Apple.SIZE,
                        Apple.SIZE
                    );
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

        // draw information
        g.setFont(INFORMATION_FONT);
        g.setColor(Color.RED);

        g.drawString(
            "Apfel-Spawn in " + JSnake.getAppleSpawningTask().getCurrentSpawnIntervall(),
            APPLE_SPAWN_TIMER_X,
            APPLE_SPAWN_TIMER_Y
        );

        repaint();
    }
}
