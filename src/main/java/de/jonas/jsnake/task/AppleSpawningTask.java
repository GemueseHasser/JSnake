package de.jonas.jsnake.task;

import de.jonas.JSnake;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Der {@link AppleSpawningTask} stellt eine sich konstant wiederholende Prozedur dar, womit das konstante neu-spawnen
 * des Apfels gehandhabt wird.
 */
@NotNull
public final class AppleSpawningTask {

    //<editor-fold desc="CONSTANTS">
    /** Die Zeit in Millisekunden, bis der Task anfangen soll, nach der initialisierung zu laufen. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int DELAY = 0;
    /** Die Zeitabstände in Millisekunden, in denen sich der Task konstant wiederholen soll. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int PERIOD = 1000;
    /** Die Zeitabstände in Sekunden, in denen sich der Apfel neu-spawnen soll. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int SPAWN_INTERVALL_IN_SECONDS = 6;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link Timer}, welcher den Task steuert bzw. worüber dieser Task läuft. */
    @NotNull
    private final Timer timer = new Timer();
    /** Das momentane Spawn-Intervall für den Apfel (bei welcher Zeit sich der Task aktuell befindet). */
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int currentSpawnIntervall = SPAWN_INTERVALL_IN_SECONDS;
    //</editor-fold>

    /**
     * Startet den Task und startet somit auch das konstante Neu-Spawnen des Apfels.
     */
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

    /**
     * Setzt das aktuelle Spawn-Intervall, bei dem sich der Task befindet, auf seinen Standard-Wert zurück.
     */
    public void resetSpawnIntervall() {
        this.currentSpawnIntervall = SPAWN_INTERVALL_IN_SECONDS;
    }

}
