package de.jonas.jsnake.object;

import de.jonas.JSnake;
import de.jonas.jsnake.constant.SnakeFieldState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.concurrent.ThreadLocalRandom;

import static de.jonas.jsnake.object.gui.Draw.GAME_RECT_SIZE;

/**
 * Der Apfel, welcher mithilfe einer {@code spawn()} Methode an einer zufälligen Position auf dem Spielfeld neu
 * platziert werden kann.
 */
@NotNull
public final class Apple {

    //<editor-fold desc="CONSTANTS">
    /** Die Größe des Apfels, in der der Apfel angezeigt werden soll. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    public static int SIZE = 7;
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das Feld, auf dem sich der Apfel momentan befindet. */
    @Getter
    @Nullable
    private SnakeField currentField;
    //</editor-fold>

    /**
     * Zufalls schon ein Apfel gespawnt wurde, wird der alte Apfel gelöscht und dann wird unabhängig davon, ob bereits
     * ein Apfel gespawnt wurde, ein neuer Apfel an einer zufälligen Position auf dem Spielfeld gespawnt.
     */
    public void spawn() {
        if (this.currentField != null) {
            if (this.currentField.getState() != SnakeFieldState.SNAKE) {
                this.currentField.setState(SnakeFieldState.NONE);
            }
        }

        this.currentField = JSnake.SNAKE_FIELDS.get(getRandomPosition());
        this.currentField.setState(SnakeFieldState.APPLE);
    }

    /**
     * Berechnet eine zufällige Zahl, welche innerhalb der Größe des Spielfeldes liegt.
     *
     * @return Eine zufällige Zahl, welche innerhalb der Größe des Spielfeldes liegt.
     */
    private int getRandomPosition() {
        return ThreadLocalRandom.current().nextInt(0, GAME_RECT_SIZE * GAME_RECT_SIZE);
    }

}
