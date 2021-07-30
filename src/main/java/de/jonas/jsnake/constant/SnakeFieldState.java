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
    ),
    APPLE(
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

}
