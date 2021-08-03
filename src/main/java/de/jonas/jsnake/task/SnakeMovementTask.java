package de.jonas.jsnake.task;

import de.jonas.JSnake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Der {@link SnakeMovementTask} stellt eine sich konstant wiederholende Prozedur dar, womit das konstante Bewegen der
 * Schlange gehandhabt wird. Sollte sich der Zustand, ob das Spiel gerade läuft, auf {@code false} befinden, bewegt sich
 * die Schlange nicht.
 */
@NotNull
public final class SnakeMovementTask {

    //<editor-fold desc="CONSTANTS">
    /** Die Zeit in Millisekunden, bis der Task anfangen soll, nach der initialisierung zu laufen. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int DELAY = 0;
    /** Die Zeitabstände in Millisekunden, in denen sich der Task konstant wiederholen soll. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int PERIOD = 500;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link Timer}, welcher den Task steuert bzw. worüber dieser Task läuft. */
    @NotNull
    private final Timer timer = new Timer();
    //</editor-fold>

    /**
     * Startet den Task und startet somit auch das konstante Bewegen der Schlange, zufalls der Zustand, ob das Spiel
     * gerade läuft, auf {@code true} befindet.
     */
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
