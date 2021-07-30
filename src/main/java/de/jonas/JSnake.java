package de.jonas;

import de.jonas.jsnake.gui.GUI;
import de.jonas.jsnake.object.Apple;
import de.jonas.jsnake.object.Snake;
import de.jonas.jsnake.object.SnakeField;
import de.jonas.jsnake.task.AppleSpawningTask;
import de.jonas.jsnake.task.SnakeMovementTask;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class JSnake {

    public static final Map<Integer, SnakeField> SNAKE_FIELDS = new HashMap<>();


    @Getter
    private static Snake snake;
    @Getter
    private static Apple apple;

    public static void main(@NotNull final String @NotNull [] args) {
        // create graphical user interface
        new GUI();

        // create snake
        snake = new Snake();

        // create apple
        apple = new Apple();

        // launch snake
        new SnakeMovementTask().start();

        // start periodic apple spawning
        new AppleSpawningTask().start();
    }

}
