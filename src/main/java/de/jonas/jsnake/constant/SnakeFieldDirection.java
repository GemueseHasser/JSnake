package de.jonas.jsnake.constant;

import lombok.Getter;
import org.jetbrains.annotations.Range;

public enum SnakeFieldDirection {

    NONE(0),
    RIGHT(1),
    LEFT(-1),
    UP(-15),
    DOWN(15);


    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private final int addition;


    SnakeFieldDirection(@Range(from = 0, to = Integer.MAX_VALUE) final int addition) {
        this.addition = addition;
    }


    public SnakeFieldDirection getOpposite() {
        if (this == RIGHT) {
            return LEFT;
        }

        if (this == LEFT) {
            return RIGHT;
        }

        if (this == UP) {
            return DOWN;
        }

        if (this == DOWN) {
            return UP;
        }

        return NONE;
    }
}
