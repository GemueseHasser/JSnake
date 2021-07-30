package de.jonas.jsnake.object;

import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

@NotNull
public final class SnakeField {

    @Getter
    @Setter
    @NotNull
    private SnakeFieldState state;
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private final int number;


    public SnakeField(@Range(from = 0, to = Integer.MAX_VALUE) final int number) {
        this.state = SnakeFieldState.NONE;
        this.number = number;
    }
}
