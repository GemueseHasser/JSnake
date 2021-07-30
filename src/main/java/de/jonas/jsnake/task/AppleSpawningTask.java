package de.jonas.jsnake.task;

import de.jonas.JSnake;

import java.util.Timer;
import java.util.TimerTask;

public final class AppleSpawningTask {

    private static final int DELAY = 0;
    private static final int PERIOD = 7000;


    private final Timer timer = new Timer();

    public void start() {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // (re)spawn apple
                JSnake.getApple().spawn();
            }
        }, DELAY, PERIOD);
    }

}
