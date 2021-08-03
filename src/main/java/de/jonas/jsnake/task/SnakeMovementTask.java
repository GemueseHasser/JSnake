package de.jonas.jsnake.task;

import de.jonas.JSnake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Timer;
import java.util.TimerTask;

@NotNull
public final class SnakeMovementTask {

    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int DELAY = 0;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int PERIOD = 500;


    @NotNull
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
