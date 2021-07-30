package de.jonas.jsnake.object;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

public final class Apple {

    public static int SIZE = 7;


    @Getter
    private SnakeField currentField;

    public void spawn() {
        if (this.currentField != null) {
            if (this.currentField.getState() != SnakeFieldState.SNAKE) {
                this.currentField.setState(SnakeFieldState.NONE);
            }
        }

        this.currentField = JSnake.SNAKE_FIELDS.get(getRandomPosition());
        this.currentField.setState(SnakeFieldState.APPLE);
    }

    private int getRandomPosition() {
        return ThreadLocalRandom.current().nextInt(0, 15 * 15);
    }

}
