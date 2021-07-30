package de.jonas.jsnake.task;

import de.jonas.JSnake;
import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;

public final class AppleSpawningTask {

    private static final int DELAY = 0;
    private static final int PERIOD = 1000;
    private static final int SPAWN_INTERVALL_IN_SECONDS = 6;


    private final Timer timer = new Timer();
    @Getter
    private int currentSpawnIntervall = SPAWN_INTERVALL_IN_SECONDS;

    public void start() {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // (re)spawn apple
                if (currentSpawnIntervall <= 0) {
                    JSnake.getApple().spawn();
                    currentSpawnIntervall = SPAWN_INTERVALL_IN_SECONDS;
                }

                currentSpawnIntervall--;
            }
        }, DELAY, PERIOD);
    }

    public void resetSpawnIntervall() {
        this.currentSpawnIntervall = SPAWN_INTERVALL_IN_SECONDS;
    }

}
