package de.jonas.jsnake.constant;

import de.jonas.jsnake.object.SnakeField;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

/**
 * Ein {@link SnakeFieldState} ist der Status, den ein {@link SnakeField} haben kann. Ein {@link SnakeFieldState}
 * besteht aus einer {@link SnakeFieldDirection Richtung} und einer {@link Color Farbe}.
 */
@NotNull
public enum SnakeFieldState {

    //<editor-fold desc="VALUES">
    /** Der Standard-Status den jedes Feld hat. */
    NONE(
        SnakeFieldDirection.NONE,
        Color.LIGHT_GRAY
    ),
    /** Ein Feld bekommt diesen Status, wenn sich ein Körperteil der Schlange in diesem befindet. */
    SNAKE(
        SnakeFieldDirection.NONE,
        Color.YELLOW
    ),
    /** Ein Feld bekommt diesen Status, wenn sich der Kopf der Schlange in diesem befindet. */
    HEAD(
        SnakeFieldDirection.NONE,
        Color.RED
    ),
    /** Ein Feld bekommt diesen Status, wenn sich der Apfel in ihm befindet. */
    APPLE(
        SnakeFieldDirection.NONE,
        Color.RED
    );
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die Farbe, die der jeweilige {@link SnakeFieldState} übergibt. */
    @Getter
    @NotNull
    private final Color color;
    /** Die {@link SnakeFieldDirection Richtung}, die der jeweilige {@link SnakeFieldState} übergibt. */
    @Setter
    @Getter
    @NotNull
    private SnakeFieldDirection direction;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt einen neuen und vollständig unabhängigen {@link SnakeFieldState}. Ein {@link SnakeFieldState} ist der
     * Status, den ein {@link SnakeField} haben kann. Ein {@link SnakeFieldState} besteht aus einer {@link
     * SnakeFieldDirection Richtung} und einer {@link Color Farbe}.
     *
     * @param direction Die {@link SnakeFieldDirection Richtung}, die der jeweilige {@link SnakeFieldState} übergibt.
     * @param color     Die Farbe, die der jeweilige {@link SnakeFieldState} übergibt.
     */
    SnakeFieldState(
        @NotNull final SnakeFieldDirection direction,
        @NotNull final Color color
    ) {
        this.direction = direction;
        this.color = color;
    }
    //</editor-fold>

}
