package de.jonas.jsnake.object.gui;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldDirection.DOWN
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldDirection.UP);
                break;

            case KeyEvent.VK_DOWN:
                if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldDirection.UP
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldDirection.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldDirection.RIGHT
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldDirection.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                if (JSnake.getSnake().getHeadField().getState().getDirection() == SnakeFieldDirection.LEFT
                    && JSnake.getSnake().getBodyPositions().size() != 0
                ) {
                    return;
                }

                JSnake.getSnake().getHeadField().getState().setDirection(SnakeFieldDirection.RIGHT);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
