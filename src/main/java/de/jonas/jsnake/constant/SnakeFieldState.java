package de.jonas.jsnake.constant;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.Color;

public enum SnakeFieldState {

    NONE(
        SnakeFieldDirection.NONE,
        Color.LIGHT_GRAY
    ),
    SNAKE(
        SnakeFieldDirection.NONE,
        Color.YELLOW
    ),
    HEAD(
        SnakeFieldDirection.NONE,
        Color.RED
    );


    @Getter
    @NotNull
    private final Color color;
    @Setter
    @Getter
    @NotNull
    private SnakeFieldDirection direction;


    SnakeFieldState(
        @NotNull final SnakeFieldDirection direction,
        @NotNull final Color color
    ) {
        this.direction = direction;
        this.color = color;
    }


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


        public SnakeFieldDirection getOpposide() {
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

}
