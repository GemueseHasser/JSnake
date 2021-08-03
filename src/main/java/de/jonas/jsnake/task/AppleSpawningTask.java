package de.jonas.jsnake.task;

import de.jonas.JSnake;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Timer;
import java.util.TimerTask;

@NotNull
public final class AppleSpawningTask {

    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int DELAY = 0;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int PERIOD = 1000;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int SPAWN_INTERVALL_IN_SECONDS = 6;


    @NotNull
    private final Timer timer = new Timer();
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
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
