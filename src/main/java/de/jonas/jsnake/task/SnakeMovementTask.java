package de.jonas.jsnake.task;

import de.jonas.JSnake;

import java.util.Timer;
import java.util.TimerTask;

public final class SnakeMovementTask {

    private static final int DELAY = 0;
    private static final int PERIOD = 500;


    private final Timer timer = new Timer();

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!JSnake.isGameRunning()) {
                    return;
                }
                JSnake.getSnake().move();
            }
        }, DELAY, PERIOD);
    }

}
