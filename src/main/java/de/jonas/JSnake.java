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
public class JSnake {

    public static final Map<Integer, SnakeField> SNAKE_FIELDS = new HashMap<>();


    @Getter
    private static Snake snake;
    @Getter
    private static Apple apple;
    @Getter
    private static AppleSpawningTask appleSpawningTask;
    private static Gui gameGui;
    @Getter
    private static WindowGui windowGui;

    @Getter
    @Setter
    private static boolean isGameRunning = true;

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


    public static void restart() {
        // close window gui
        windowGui.close();

        // set game running
        isGameRunning = true;

        // set snake direction to 'NONE'
        snake.getHeadField().getState().setDirection(SnakeFieldDirection.NONE);

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
