package de.jonas;

import de.jonas.jsnake.constant.SnakeFieldDirection;
import de.jonas.jsnake.gui.Gui;
import de.jonas.jsnake.gui.WindowGui;
import de.jonas.jsnake.object.Apple;
import de.jonas.jsnake.object.Snake;
import de.jonas.jsnake.object.SnakeField;
import de.jonas.jsnake.task.AppleSpawningTask;
import de.jonas.jsnake.task.SnakeMovementTask;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

// grafik überarbeiten
// *** write Javadoc ***
// editor-folds

/**
 * <p>Dies ist die Haupt- und Main-Klasse dieser Anwendung. Diese Anwendung spiegelt das Spiel 'Snake' wieder. In
 * dieser Klasse werden alle Nötigen Klassen für den Start des Spiels initialisiert. Durch diese Haupt-Klasse kommt man
 * in allen anderen Klassen sortiert an die Haupt-Komponenten des Spiels heran. Alles läuft gebündelt über diese
 * Klasse.</p>
 * <p>Diese Klasse beinhaltet die Main-Methode, die der JRE übergeben wird und somit als aller erstes, vor allen
 * anderen Klassen bzw. Methoden, aufgerufen wird.</p>
 */
@NotNull
public class JSnake {

    //<editor-fold desc="CONSTANTS">
    /**
     * Alle {@link SnakeField Felder} aus dem Spiel werden hier geordnet gespeichert, damit man auf diese mithilfe einer
     * Nummer, welche oben links bei 0 anfängt und nach rechts läuft (über alle Reihen hinweg), sehr einfach und sicher
     * zugreifen kann.
     */
    @NotNull
    public static final Map<Integer, SnakeField> SNAKE_FIELDS = new HashMap<>();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die Schlange aus dem Spiel, welche sich über das Spielfeld bewegt. */
    @Getter
    private static Snake snake;
    /** Der Apfel des Spiels, der nach einer gewissen Zeit immer wieder woanders auftaucht. */
    @Getter
    private static Apple apple;
    /** Der Task, welcher konstant läuft und das konstante Spawnen des Apfels regelt. */
    @Getter
    private static AppleSpawningTask appleSpawningTask;
    /** Das {@link Gui Graphical User Interface}, worauf das gesamte Spiel angezeigt wird (das Hauptfenster). */
    private static Gui gameGui;
    /** Das Pop-Up Fenster, welches das 'Verloren' anzeigt, wenn man verloren hat. */
    @Getter
    private static WindowGui windowGui;

    /** Der Zustand, ob das Spiel läuft, oder ob es momentan still-stehen soll. */
    @Getter
    @Setter
    private static boolean isGameRunning = true;
    //</editor-fold>


    //<editor-fold desc="main">

    /**
     * Die Main-Methode der Anwendung, von der aus alle nötigen Instanzen für das Spiel erzeugt werden und welche als
     * aller erstes von der JRE aufgerufen wird.
     *
     * @param args Die Argumente, die beim Starten der Anwendung übergeben werden.
     */
    public static void main(@NotNull final String @NotNull [] args) {
        // create graphical user interface
        gameGui = new Gui();

        // create window graphical user interface
        windowGui = new WindowGui();

        // create snake
        snake = new Snake();

        // create apple
        apple = new Apple();

        // launch snake
        new SnakeMovementTask().start();

        // start periodic apple spawning
        appleSpawningTask = new AppleSpawningTask();
        appleSpawningTask.start();

        // open graphical user interface
        gameGui.open();
    }
    //</editor-fold>


    /**
     * Startet das Spiel neu bzw. resettet alle Komponenten des Spiels.
     */
    public static void restart() {
        // close window gui
        windowGui.close();

        // set game running
        isGameRunning = true;

        // set snake direction to 'NONE'
        final SnakeField head = snake.getHeadField();

        assert head != null;
        head.getState().setDirection(SnakeFieldDirection.NONE);

        // recreate snake
        snake = new Snake();

        // recreate apple
        apple = new Apple();

        // clear given snake-fields
        SNAKE_FIELDS.clear();

        // reload graphical user interface
        gameGui.reload();

        // reset apple spawn-intervall
        appleSpawningTask.resetSpawnIntervall();

        // spawn apple
        apple.spawn();
    }

}
