package de.jonas.jsnake.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import de.jonas.jsnake.object.SnakeField;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class GUI {

    private static final String TITLE = "Snake - by Jonas";

    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;


    public GUI() {
        final JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.addKeyListener(new KeyHandler());

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        frame.add(draw);
        frame.setVisible(true);
    }


    private static final class Draw extends JLabel {

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
                    g.fillRect(x, y, GAME_RECTS_SIZE, GAME_RECTS_SIZE);

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

    private static final class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldState.SnakeFieldDirection.DOWN) {
                        return;
                    }

                    JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldState.SnakeFieldDirection.UP);
                    break;

                case KeyEvent.VK_DOWN:
                    if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldState.SnakeFieldDirection.UP) {
                        return;
                    }

                    JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldState.SnakeFieldDirection.DOWN);
                    break;

                case KeyEvent.VK_LEFT:
                    if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldState.SnakeFieldDirection.RIGHT) {
                        return;
                    }

                    JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldState.SnakeFieldDirection.LEFT);
                    break;

                case KeyEvent.VK_RIGHT:
                    if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldState.SnakeFieldDirection.LEFT) {
                        return;
                    }

                    JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldState.SnakeFieldDirection.RIGHT);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

}
