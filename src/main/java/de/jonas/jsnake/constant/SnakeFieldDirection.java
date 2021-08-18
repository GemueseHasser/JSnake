package de.jonas.jsnake.constant;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Eine {@link SnakeFieldDirection} besteht nur aus einer Zahl, welche genutzt wird, um das nächste Feld in dieser
 * Richtung zu berechnen.
 */
@NotNull
public enum SnakeFieldDirection {

    //<editor-fold desc="VALUES">
    /** Keine Richtung. */
    NONE(0),
    /** Richtung nach Rechts. */
    RIGHT(1),
    /** Richtung nach Links. */
    LEFT(-1),
    /** Richtung nach Oben. */
    UP(-15),
    /** Richtung nach Unten. */
    DOWN(15);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Die Zahl, die genutzt wird, um das nächste Feld in dieser Richtung zu berechnen. */
    @Getter
    @Range(from = 0, to = Integer.MAX_VALUE)
    private final int addition;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Erzeugt eine neue und vollständig unabhängige {@link SnakeFieldDirection}. Eine {@link SnakeFieldDirection}
     * besteht nur aus einer Zahl, welche genutzt wird, um das nächste Feld in dieser Richtung zu berechnen.
     *
     * @param addition Die Zahl, die genutzt wird, um das nächste Feld in dieser Richtung zu berechnen.
     */
    SnakeFieldDirection(@Range(from = 0, to = Integer.MAX_VALUE) final int addition) {
        this.addition = addition;
    }
    //</editor-fold>


    /**
     * Berechnet und gibt die gegensätzliche {@link SnakeFieldDirection Richtung} zu der jeweiligen {@link
     * SnakeFieldDirection Richtung} der Instanz zurück.
     *
     * @return Die gegensätzliche {@link SnakeFieldDirection Richtung} zu der jeweiligen {@link SnakeFieldDirection
     *     Richtung} der Instanz zurück.
     */
    @NotNull
    public SnakeFieldDirection getOpposite() {
        switch (this) {
            case LEFT:
                return RIGHT;

            case RIGHT:
                return LEFT;

            case UP:
                return DOWN;

            case DOWN:
                return UP;

            default:
                return NONE;
        }
    }
}
